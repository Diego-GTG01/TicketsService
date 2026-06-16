package com.risosuit.DGomezTagle.TicketsService.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.EstadoTicket;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EstadoDAOImplementation implements IEstado {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result getAll() {

        Result<EstadoTicket> result = new Result<EstadoTicket>();
        try {
            TypedQuery<EstadoTicket> query = entityManager.createQuery("From EstadoTicket", EstadoTicket.class);
            List<EstadoTicket> allEstados = query.getResultList();
            if (allEstados.isEmpty()) {
                result.correct = false;
                result.message = "No hay prioridades";

            } else {
                result.correct = true;
                result.message = "Exito obteniendo prioridades";
                result.objects = new ArrayList<>(allEstados);

            }

        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result<EstadoTicket> getByName(String nombre) {

        Result<EstadoTicket> result = new Result<EstadoTicket>();
        try {
            TypedQuery<EstadoTicket> query = entityManager.createQuery("From EstadoTicket WHERE nombre = :nombre",
                    EstadoTicket.class);
            query.setParameter("nombre", nombre);
            EstadoTicket estado = query.getSingleResult();
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

}
