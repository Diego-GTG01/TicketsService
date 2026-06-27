package com.risosuit.DGomezTagle.TicketsService.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
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
    public Result<Historial> getHistorialByIdTicket(long idTicket) {
        Result<Historial> result = new Result<Historial>();
        try {
            String jpql = "SELECT h FROM Historial h "
                    + "LEFT JOIN FETCH h.usuario "
                    + "LEFT JOIN FETCH h.estadoAnterior "
                    + "LEFT JOIN FETCH h.estadoActual "
                    + "WHERE h.ticket.idTicket = :idTicket";

            TypedQuery<Historial> query = entityManager.createQuery(jpql, Historial.class);
            query.setParameter("idTicket", idTicket);
            List<Historial> historial = query.getResultList();

            if (historial.isEmpty()) {
                result.correct = false;
                result.message = "No se encontró historial para este ticket";
            } else {
                result.correct = true;
                result.message = "Éxito obteniendo historial";
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
            historial.setFechaActualizacion(new Date());

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
        throw new UnsupportedOperationException("Unimplemented method 'liberarTicket'");
    }

    @Override
    public Result<Historial> updatePrioridad(Historial historial) {
        throw new UnsupportedOperationException("Unimplemented method 'updatePrioridad'");
    }
}
