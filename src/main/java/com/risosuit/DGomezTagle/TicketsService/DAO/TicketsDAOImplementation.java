package com.risosuit.DGomezTagle.TicketsService.DAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.DTO.UsuarioDTO;
import com.risosuit.DGomezTagle.TicketsService.JPA.EstadoTicket;
import com.risosuit.DGomezTagle.TicketsService.JPA.Prioridad;
import com.risosuit.DGomezTagle.TicketsService.JPA.Rol;
import com.risosuit.DGomezTagle.TicketsService.JPA.Ticket;
import com.risosuit.DGomezTagle.TicketsService.JPA.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class TicketsDAOImplementation implements ITicket {

    @Autowired
    private EstadoDAOImplementation estadoDAO;

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

    @Override
    public Result<Ticket> getByIdTicket(int idTicket) {
        Result<Ticket> result = new Result<>();
        try {
            Ticket ticket = entityManager.find(Ticket.class, idTicket);
            if (ticket == null) {
                result.correct = false;
                result.message = "Ticket no encontrado";
            } else {
                result.correct = true;
                result.message = "Ticket encontrado";
                result.object = ticket;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result<Ticket> getByIdUsuarioSolicitado(int idUsuarioSolicitado) {
        Result<Ticket> result = new Result<>();
        try {
            TypedQuery query = entityManager.createQuery(
                    "SELECT t FROM Ticket t WHERE t.usuarioSolicitante.idUsuario = :idUsuario", Ticket.class);
            query.setParameter("idUsuario", idUsuarioSolicitado);
            List<Ticket> tickets = query.getResultList();

            if (tickets.isEmpty()) {
                result.correct = false;
                result.message = "Tickets no encontrado";
            } else {
                result.correct = true;
                result.message = "Ticket encontrado";
                result.objects = new ArrayList<>(tickets);
            }
        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result<Ticket> getByIdUsuarioAgente(int idUsuarioAgente) {
        Result<Ticket> result = new Result<>();
        try {
            TypedQuery query = entityManager.createQuery(
                    "SELECT t FROM Ticket t WHERE t.agenteAsignado.idUsuario = :idUsuario", Ticket.class);
            query.setParameter("idUsuario", idUsuarioAgente);
            List<Ticket> tickets = query.getResultList();

            if (tickets.isEmpty()) {
                result.correct = false;
                result.message = "Tickets no encontrado";
            } else {
                result.correct = true;
                result.message = "Ticket encontrado";
                result.objects = new ArrayList<>(tickets);
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
    public Result<Ticket> addTicket(Ticket ticket) {
        Result<Ticket> result = new Result<>();
        try {
            if (ticket != null) {
                ticket.setFechaActualizacion(new Date());
                ticket.setFechaCreacion(new Date());
                ticket.setAgenteAsignado(null);
                Result<EstadoTicket> resultEstado = estadoDAO.getByName("Abierto");
                if (resultEstado.correct) {
                    ticket.setEstado((EstadoTicket) resultEstado.object);
                }

                entityManager.persist(ticket);
                entityManager.flush();

                result.correct = true;
                result.object = ticket;
                result.message = "Ticket creado correctamente";

            } else {
                result.correct = false;
                result.message = "Ticket invalido";
            }

        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

            result.correct = false;
            result.message = "Error al guardar el ticket: " + ex.getLocalizedMessage();
        }

        return result;
    }

    @Transactional
    @Override
    public Result<Ticket> asignarTicket(int idTicket, int idAgente) {
        Result<Ticket> result = new Result<>();
        try {
            Ticket ticket = entityManager.find(Ticket.class, idTicket);

            if (ticket == null) {
                result.correct = false;
                result.message = "Ticket no encontrado";
                return result;
            }
            Usuario agente = entityManager.find(
                    Usuario.class,
                    idAgente);
            if (agente == null) {
                result.correct = false;
                result.message = "Agente no encontrado";
                return result;
            }
            Rol rol = entityManager.find(Rol.class, agente.getRol().getIdRol());
            if (rol == null) {
                result.correct = false;
                result.message = "Agente sin rol";
                return result;
            }
            if (!rol.getNombre().equals("Agente") && !rol.getNombre().equals("Administrador")) {
                result.correct = false;
                result.message = "Agente con rol no permitido";
                return result;
            }
            ticket.setAgenteAsignado(agente);
            entityManager.merge(ticket);
            result.correct = true;
            result.object = ticket;
            result.message = "Agente asignado";

        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result<Ticket> updatePrioridad(
            int idTicket,
            int idPrioridad) {

        Result<Ticket> result = new Result<>();

        try {

            Ticket ticket = entityManager.find(Ticket.class, idTicket);

            if (ticket == null) {
                result.correct = false;
                result.message = "Ticket no encontrado";
                return result;
            }

            Prioridad prioridad = entityManager.find(Prioridad.class, idPrioridad);

            ticket.setPrioridad(prioridad);

            entityManager.merge(ticket);

            result.correct = true;
            result.object = ticket;

        } catch (Exception ex) {

            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result<Ticket> updateEstado(
            int idTicket,
            int idEstado) {

        Result<Ticket> result = new Result<>();

        try {

            Ticket ticket = entityManager.find(Ticket.class, idTicket);

            if (ticket == null) {
                result.correct = false;
                result.message = "Ticket no encontrado";
                return result;
            }

            EstadoTicket estado = entityManager.find(
                    EstadoTicket.class,
                    idEstado);

            ticket.setEstado(estado);

            entityManager.merge(ticket);

            result.correct = true;
            result.object = ticket;
            result.message = "Estado actualizado";

        } catch (Exception ex) {

            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

}
