package com.risosuit.DGomezTagle.TicketsService.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Comentario;
import com.risosuit.DGomezTagle.TicketsService.JPA.EstadoTicket;
import com.risosuit.DGomezTagle.TicketsService.JPA.Historial;
import com.risosuit.DGomezTagle.TicketsService.JPA.Ticket;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class HistorialDAOImplementation implements IHistorial {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result<Historial> getHistorialByIdTicket(int idTicket) {
        Result<Historial> result = new Result<Historial>();
        try {
            TypedQuery<Historial> query = entityManager.createQuery(
                    "From Historial c WHERE c.ticket.idTicket = :idTicket",
                    Historial.class);
            query.setParameter("idTicket", idTicket);
            List<Historial> historial = query.getResultList();
            if (historial == null) {
                result.correct = false;
                result.message = "No hay prioridades";

            } else {
                result.correct = true;
                result.message = "Exito obteniendo prioridades";
                result.objects = new ArrayList<>(historial);

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
    public Result<Historial> updateEstadoTicket(Historial historial) {
        Result<Historial> result = new Result<>();

        try {
            Ticket ticket = entityManager.find(Ticket.class, historial.getTicket().getIdTicket());
            ticket.setEstado(historial.getEstadoActual());
            ticket.setFechaActualizacion(new Date());
            historial.setFechaActualizaciion(new Date());

            entityManager.persist(historial);
            entityManager.merge(ticket);

            result.correct = true;
            result.message = "Estado actualizado correctamente";
            result.object = historial;

        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result<Historial> liberarTicket(EstadoTicket estadoTicket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'liberarTicket'");
    }

    @Override
    public Result<Historial> updatePrioridad(Historial historial) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePrioridad'");
    }

}
