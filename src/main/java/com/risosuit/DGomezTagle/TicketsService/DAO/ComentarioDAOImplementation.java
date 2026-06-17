package com.risosuit.DGomezTagle.TicketsService.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Comentario;
import com.risosuit.DGomezTagle.TicketsService.JPA.EstadoTicket;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class ComentarioDAOImplementation implements IComentario {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result<Comentario> getComentarioByIdTicket(int idTicket) {

        Result<Comentario> result = new Result<Comentario>();
        try {
            TypedQuery<Comentario> query = entityManager.createQuery("From Comentario c WHERE c.ticket.idTicket = :idTicket",
                    Comentario.class);
            query.setParameter("idTicket", idTicket);
            List<Comentario> estado = query.getResultList();
            if (estado == null) {
                result.correct = false;
                result.message = "No hay prioridades";

            } else {
                result.correct = true;
                result.message = "Exito obteniendo prioridades";
                result.object = estado;

            }

        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result<Comentario> addComentario(Comentario comentario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addComentario'");
    }

}
