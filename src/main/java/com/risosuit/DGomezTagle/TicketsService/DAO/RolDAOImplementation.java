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

    public Rol getRolUsuarioPorNombre() {
        try {
            // Buscamos directamente el rol donde el nombre sea 'Usuario'
            TypedQuery<Rol> query = entityManager.createQuery(
                    "FROM Rol WHERE nombre = :nombreRol", Rol.class);
            query.setParameter("nombreRol", "Usuario"); // Ajusta "nombre" si en tu entidad Rol se llama 'nombreRol', 'descripcion', etc.

            return query.getSingleResult();
        } catch (Exception ex) {
            // Si no existe el rol o hay error, manejas la excepción
            System.out.println("Error al buscar el rol Usuario: " + ex.getMessage());
            return null;
        }
    }

}
