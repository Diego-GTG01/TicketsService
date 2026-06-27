package com.risosuit.DGomezTagle.TicketsService.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.DTO.UsuarioDTO;
import com.risosuit.DGomezTagle.TicketsService.JPA.Ticket;
import com.risosuit.DGomezTagle.TicketsService.JPA.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class UsuarioDAOImplementation implements IUsuario {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RolDAOImplementation rolDAO;

    @Override
    public Result<Usuario> getByUsername(String username) {

        Result<Usuario> result = new Result();

        try {
            TypedQuery<Usuario> query = entityManager.createQuery(
                    "SELECT u FROM Usuario u "
                    + "JOIN FETCH u.rol "
                    + "WHERE u.username = :username",
                    Usuario.class
            );

            query.setParameter("username", username);

            Usuario user = query.getSingleResult();

            result.object = user;
            result.correct = true;
            result.message = "Usuario encontrado";

        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getMessage();
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
    public Result getByRol(String nombre) {
        Result<Usuario> result = new Result();
        try {
            TypedQuery<Usuario> query = entityManager.createQuery(
                    "FROM Usuario u WHERE u.rol.nombre = :rol",
                    Usuario.class);
            query.setParameter("rol", nombre);
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
    public Result getAll() {
        Result<Usuario> result = new Result();
        try {
            TypedQuery<Usuario> query = entityManager.createQuery(
                    "FROM Usuario",
                    Usuario.class);
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

    @Transactional
    @Override
    public Result addUsuario(Usuario usuario) {
        Result<Usuario> result = new Result<>();

        if (usuario == null) {
            result.correct = false;
            result.message = "Usuario no válido";
            return result;
        }

        try {
            TypedQuery<Usuario> query = entityManager.createQuery(
                    "SELECT u FROM Usuario u WHERE u.email = :email OR u.username = :username", Usuario.class);
            query.setParameter("email", usuario.getEmail());
            query.setParameter("username", usuario.getUsername());

            List<Usuario> resultados = query.getResultList();

            if (!resultados.isEmpty()) {
                Usuario existente = resultados.get(0);
                result.correct = false;

                if (existente.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                    result.message = "El correo ya está registrado";
                } else if (existente.getUsername().equalsIgnoreCase(usuario.getUsername())) {
                    result.message = "El nombre de usuario ya está en uso";
                } else {
                    result.message = "El usuario o correo ya existen";
                }
                return result;
            }

            if (usuario.getRol() == null) {
                usuario.setRol(rolDAO.getRolUsuarioPorNombre());
            }
            usuario.setActivo(0);

            entityManager.persist(usuario);
            result.message = "Usuario registrado con éxito";
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.message = "Error en la operación: " + ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Transactional
    @Override
    public Result updateUsuario(Usuario usuarioRecibido) {
        Result<Usuario> result = new Result();
        try {
            if (usuarioRecibido == null) {
                result.correct = false;
                result.message = "Usuarios no Valido";
            } else {
                Usuario usuario = entityManager.find(Usuario.class, usuarioRecibido.getIdUsuario());
                usuario.setActivo(usuarioRecibido.getActivo());
                usuario.setApellidoMaterno(usuarioRecibido.getApellidoMaterno());
                usuario.setApellidoPaterno(usuarioRecibido.getApellidoPaterno());
                usuario.setCelular(usuarioRecibido.getCelular());
                usuario.setEmail(usuarioRecibido.getEmail());
                usuario.setNombre(usuarioRecibido.getNombre());
                usuario.setRol(usuarioRecibido.getRol());
                usuario.setTelefono(usuarioRecibido.getTelefono());
                usuario.setUsername(usuarioRecibido.getUsername());

                entityManager.merge(usuario);

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

    @Transactional
    @Override
    public Result deleteUsuario(int idUsuario) {
        Result result = new Result();
        try {

            if (idUsuario == 0) {
                result.correct = false;
                result.message = "Usuario no válido";
                return result;
            }

            Usuario usuario = entityManager.find(Usuario.class, idUsuario);

            if (usuario == null) {
                result.correct = false;
                result.message = "Usuario no encontrado";
                return result;
            }
            String jpql = "SELECT t FROM Ticket t "
                    + "LEFT JOIN FETCH t.usuarioSolicitante "
                    + "LEFT JOIN FETCH t.agenteAsignado WHERE t.agenteAsignado.idUsuario = :idUsuario";

            TypedQuery<Ticket> query = entityManager.createQuery(jpql, Ticket.class);
            query.setParameter("idUsuario", (long) usuario.getIdUsuario());
            List<Ticket> tickets = query.getResultList();
            for (Ticket ticket : tickets) {
                entityManager.remove(ticket);
            }
            entityManager.flush();

            jpql = "SELECT t FROM Ticket t "
                    + "LEFT JOIN FETCH t.usuarioSolicitante "
                    + "LEFT JOIN FETCH t.agenteAsignado WHERE t.usuarioSolicitante.idUsuario = :idUsuario";

            query = entityManager.createQuery(jpql, Ticket.class);
            query.setParameter("idUsuario", (long) usuario.getIdUsuario());
            tickets = query.getResultList();
            for (Ticket ticket : tickets) {
                entityManager.remove(ticket);
            }
            entityManager.flush();

            entityManager.remove(usuario);
            entityManager.flush();

            result.correct = true;
            result.object = usuario;

        } catch (Exception e) {

            result.correct = false;
            result.message = e.getLocalizedMessage();
            result.ex = e;

        }

        return result;
    }

    @Override
    public Result updatePassword(int idUsuario, String password) {
        Result result = new Result();
        try {
            Usuario usuario = entityManager.find(Usuario.class, idUsuario);
            if (usuario != null) {
                entityManager.merge(usuario);
                result.correct = true;
                result.message = "Password Update";
                result.object = usuario;
            } else {
                result.correct = false;
                result.message = "Usuario no ecnotrado";

            }
            usuario.setPassword(password);
        } catch (Exception e) {
            result.correct = false;
            result.message = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }

}
