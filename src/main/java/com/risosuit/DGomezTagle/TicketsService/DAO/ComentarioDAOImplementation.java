package com.risosuit.DGomezTagle.TicketsService.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Comentario;
import com.risosuit.DGomezTagle.TicketsService.JPA.Historial;
import com.risosuit.DGomezTagle.TicketsService.JPA.Ticket;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class ComentarioDAOImplementation implements IComentario {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private HistorialDAOImplementation historialDAO;

    @Override
    public Result<Comentario> getComentarioByIdTicket(long idTicket) {
        Result<Comentario> result = new Result<Comentario>();
        try {
            String jpql = "SELECT c FROM Comentario c "
                    + "INNER JOIN FETCH c.usuario "
                    + "WHERE c.ticket.idTicket = :idTicket";

            TypedQuery<Comentario> query = entityManager.createQuery(jpql, Comentario.class);
            query.setParameter("idTicket", idTicket);

            List<Comentario> comentarios = query.getResultList();

            if (comentarios.isEmpty()) {
                result.correct = false;
                result.message = "No se encontraron comentarios para este ticket";
            } else {
                result.correct = true;
                result.message = "Exito obteniendo comentarios";
                result.objects = new ArrayList<>(comentarios);
            }

        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Transactional
    @Override
    public Result<Comentario> addComentario(Comentario comentario) {
        Result<Comentario> result = new Result<Comentario>();
        try {
            if (comentario != null) {
                Ticket ticket = entityManager.find(Ticket.class, comentario.getTicket().getIdTicket());

                Historial historial = new Historial();
                historial.setUsuario(comentario.getUsuario());
                historial.setEstadoActual(ticket.getEstado());
                historial.setEstadoAnterior(ticket.getEstado());
                historial.setFechaActualizacion(new Date());
                historial.setTicket(ticket);
                historial.setIdHistorial(0);
                historial.setDescripcionCambio("Agregó un comentario");
                historial.setticket(ticket);

                Result resultHistorial = historialDAO.updateEstadoTicket(historial);
                if (!resultHistorial.correct) {
                    TransactionAspectSupport
                            .currentTransactionStatus()
                            .setRollbackOnly();

                    result.correct = false;
                    result.message = "Error al guardar historial";

                    return result;
                } else {
                    entityManager.persist(comentario);
                    result.correct = true;
                    result.message = "comentario guardado";
                }

            } else {
                result.correct = false;
                result.message = "comentarioVacio";
            }

        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }
}
