package com.risosuit.DGomezTagle.TicketsService.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Comentario;
import com.risosuit.DGomezTagle.TicketsService.JPA.EstadoTicket;
import com.risosuit.DGomezTagle.TicketsService.JPA.Historial;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

    @Override
    public Result<Historial> updateEstadoTicket(EstadoTicket estadoTicket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEstadoTicket'");
    }

    @Override
    public Result<Historial> updatePrioridad(EstadoTicket estadoTicket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePrioridad'");
    }

    @Override
    public Result<Historial> liberarTicket(EstadoTicket estadoTicket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'liberarTicket'");
    }

}
