package com.risosuit.DGomezTagle.TicketsService.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.DTO.UsuarioDTO;
import com.risosuit.DGomezTagle.TicketsService.JPA.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import com.risosuit.DGomezTagle.TicketsService.RestController.TicketsRestController;

@Repository
public class UsuarioDAOImplementation implements IUsuario {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Result<Usuario> getByUsername(String username) {
        Result<Usuario> result = new Result();
        try {
            TypedQuery<Usuario> query = entityManager.createQuery(
                    "SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class);
            query.setParameter("username", username);
            Usuario user = query.getSingleResult();
            if (user == null) {
                result.correct = false;
                result.message = "Usuario no encontrado";
            } else {
                result.object = user;

                result.message = "Usuario encontrados";
                result.correct = true;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result getAllAgentes() {
        Result<Usuario> result = new Result();
        try {
            TypedQuery<Usuario> query = entityManager.createQuery(
                    "FROM Usuario u WHERE u.rol.idRol = :rol",
                    Usuario.class);
            query.setParameter("rol", 2);
            List<Usuario> usuarios = query.getResultList();

            if (usuarios.isEmpty()) {
                result.correct = false;
                result.message = "Usuarios no encontrado";
            } else {
                result.objects = new ArrayList<>(usuarios);

                result.message = "Usuario encontrados";
                result.correct = true;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result getByRol(int idRol) {
        Result<Usuario> result = new Result();
        try {
            TypedQuery<Usuario> query = entityManager.createQuery(
                    "FROM Usuario u WHERE u.rol.idRol = :rol",
                    Usuario.class);
            query.setParameter("rol", idRol);
            List<Usuario> usuarios = query.getResultList();

            if (usuarios.isEmpty()) {
                result.correct = false;
                result.message = "Usuarios no encontrado";
            } else {
                result.objects = new ArrayList<>(usuarios);

                result.message = "Usuario encontrados";
                result.correct = true;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

}
