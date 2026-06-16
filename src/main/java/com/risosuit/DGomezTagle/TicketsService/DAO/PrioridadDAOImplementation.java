package com.risosuit.DGomezTagle.TicketsService.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Prioridad;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class PrioridadDAOImplementation implements IPrioridad {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result<Prioridad> getAll() {
        Result<Prioridad> result = new Result<Prioridad>();
        try {
            TypedQuery<Prioridad> query = entityManager.createQuery("From Prioridad", Prioridad.class);
            List<Prioridad> allPrioridades = query.getResultList();
            if (allPrioridades.isEmpty()) {
                result.correct = false;
                result.message = "No hay prioridades";

            } else {
                result.correct = true;
                result.message = "Exito obteniendo prioridades";
                result.objects = new ArrayList<>(allPrioridades);

            }

        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

}
