package com.risosuit.DGomezTagle.TicketsService.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Ticket;
import com.risosuit.DGomezTagle.TicketsService.JPA.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class TicketsDAOImplementation implements ITicket {

    private final EntityManager entityManager;

    TicketsDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Result<Ticket> getAll() {
        Result<Ticket> result = new Result<>();
        try {
            TypedQuery<Ticket> query = entityManager.createQuery("From Ticket", Ticket.class);
            List<Ticket> tickets = query.getResultList();
            result.objects = new ArrayList<>(tickets);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

}
