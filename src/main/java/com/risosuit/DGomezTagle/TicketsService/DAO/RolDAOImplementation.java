package com.risosuit.DGomezTagle.TicketsService.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Rol;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class RolDAOImplementation implements IRol {

    private final EntityManager entityManager;

    RolDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Result getAllRol() {
        Result result = new Result();
        try {
            TypedQuery<Rol> roles = entityManager.createQuery("From Rol", Rol.class);
            List<Rol> allRoles = roles.getResultList();
            if (allRoles.isEmpty()) {
                result.correct = false;
                result.message = "No Hay Roles";

            } else {
                result.objects = new ArrayList<>(allRoles);
                result.correct = true;
                result.message = "Busqueda de Roles";
            }

        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

}
