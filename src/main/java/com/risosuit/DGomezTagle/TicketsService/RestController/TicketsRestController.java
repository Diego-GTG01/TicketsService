package com.risosuit.DGomezTagle.TicketsService.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.risosuit.DGomezTagle.TicketsService.DAO.TicketsDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.DTO.TicketDTO;
import com.risosuit.DGomezTagle.TicketsService.DTO.UsuarioDTO;

import com.risosuit.DGomezTagle.TicketsService.JPA.Usuario;
import com.risosuit.DGomezTagle.TicketsService.JPA.Ticket;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "http://localhost:4200")
public class TicketsRestController {

    private final TicketsDAOImplementation ticketsDAO;

    public TicketsRestController(TicketsDAOImplementation ticketsDAO) {
        this.ticketsDAO = ticketsDAO;
    }

    @GetMapping("getAll")
    public ResponseEntity<Result<TicketDTO>> getAll() {

        Result<Ticket> resultJPA = ticketsDAO.getAll();

        Result<TicketDTO> resultDTO = new Result<>();
        resultDTO.correct = resultJPA.correct;
        resultDTO.message = resultJPA.message;

        if (!resultDTO.correct) {
            return ResponseEntity.badRequest().body(resultDTO);
        }

        if (resultJPA.objects.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        resultDTO.objects = new ArrayList<>();

        for (Ticket ticket : resultJPA.objects) {
            resultDTO.objects.add(mapTicketJPAtoDTO(ticket));
        }

        return ResponseEntity.ok(resultDTO);
    }

    @GetMapping
    public ResponseEntity<Result<TicketDTO>> getByIdTicket(@RequestParam int idTicket) {

        Result<Ticket> resultJPA = ticketsDAO.getByIdTicket(idTicket);

        Result<TicketDTO> resultDTO = new Result<>();
        resultDTO.correct = resultJPA.correct;
        resultDTO.message = resultJPA.message;

        if (!resultDTO.correct) {
            return ResponseEntity.badRequest().body(resultDTO);
        }

        if (resultJPA.object == null) {
            return ResponseEntity.noContent().build();
        }

        resultDTO.object = mapTicketJPAtoDTO((Ticket) resultJPA.object);

        return ResponseEntity.ok(resultDTO);
    }

    @GetMapping("ByUsuarioSolicitado")
    public ResponseEntity<Result<TicketDTO>> getByIdUsuarioSolicitado(@RequestParam int idUsuario) {

        Result<Ticket> resultJPA = ticketsDAO.getByIdUsuarioSolicitado(idUsuario);

        Result<TicketDTO> resultDTO = new Result<>();
        resultDTO.correct = resultJPA.correct;
        resultDTO.message = resultJPA.message;

        if (!resultDTO.correct) {
            return ResponseEntity.badRequest().body(resultDTO);
        }

        if (resultJPA.objects.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        resultDTO.objects = new ArrayList<>();

        for (Ticket ticket : resultJPA.objects) {
            resultDTO.objects.add(mapTicketJPAtoDTO(ticket));
        }

        return ResponseEntity.ok(resultDTO);
    }

    @GetMapping("ByAgenteAsignado")
    public ResponseEntity<Result<TicketDTO>> getByIdUsuarioAgente(@RequestParam int idUsuario) {

        Result<Ticket> resultJPA = ticketsDAO.getByIdUsuarioAgente(idUsuario);

        Result<TicketDTO> resultDTO = new Result<>();
        resultDTO.correct = resultJPA.correct;
        resultDTO.message = resultJPA.message;

        if (!resultDTO.correct) {
            return ResponseEntity.badRequest().body(resultDTO);
        }

        if (resultJPA.objects.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        resultDTO.objects = new ArrayList<>();

        for (Ticket ticket : resultJPA.objects) {
            resultDTO.objects.add(mapTicketJPAtoDTO(ticket));
        }

        return ResponseEntity.ok(resultDTO);
    }

    @PostMapping
    public ResponseEntity<Result<TicketDTO>> addTicket(@RequestBody Ticket ticket) {

        Result<Ticket> resultJPA = ticketsDAO.addTicket(ticket);

        Result<TicketDTO> resultDTO = new Result<>();
        resultDTO.correct = resultJPA.correct;
        resultDTO.message = resultJPA.message;
        resultDTO.ex = resultJPA.ex;

        if (!resultDTO.correct) {
            return ResponseEntity.badRequest().body(resultDTO);
        }

        if (resultJPA.object == null) {
            return ResponseEntity.noContent().build();
        }

        resultDTO.object = mapTicketJPAtoDTO((Ticket) resultJPA.object);

        return ResponseEntity.ok(resultDTO);
    }

    @PatchMapping("assignTo")
    public ResponseEntity<Result<TicketDTO>> asignarTicket(@RequestParam int idTicket,
            @RequestParam int idAgenteAsignado) {

        Result<Ticket> resultJPA = ticketsDAO.asignarTicket(idTicket, idAgenteAsignado);

        Result<TicketDTO> resultDTO = new Result<>();
        resultDTO.correct = resultJPA.correct;
        resultDTO.message = resultJPA.message;
        resultDTO.ex = resultJPA.ex;

        if (!resultDTO.correct) {
            return ResponseEntity.badRequest().body(resultDTO);
        }

        if (resultJPA.object == null) {
            return ResponseEntity.noContent().build();
        }

        resultDTO.object = mapTicketJPAtoDTO((Ticket) resultJPA.object);

        return ResponseEntity.ok(resultDTO);
    }

    @PatchMapping("status")
    public ResponseEntity<Result<TicketDTO>> updateStatus(@RequestParam int idTicket,
            @RequestParam int status) {

        Result<Ticket> resultJPA = ticketsDAO.updateStatus(idTicket, status);

        Result<TicketDTO> resultDTO = new Result<>();
        resultDTO.correct = resultJPA.correct;
        resultDTO.message = resultJPA.message;
        resultDTO.ex = resultJPA.ex;

        if (!resultDTO.correct) {
            return ResponseEntity.badRequest().body(resultDTO);
        }

        if (resultJPA.object == null) {
            return ResponseEntity.noContent().build();
        }

        resultDTO.object = mapTicketJPAtoDTO((Ticket) resultJPA.object);

        return ResponseEntity.ok(resultDTO);
    }

    @PatchMapping("estado")
    public ResponseEntity<Result<TicketDTO>> updateEstado(@RequestParam int idTicket,
            @RequestParam int estado) {

        Result<Ticket> resultJPA = ticketsDAO.updateEstado(idTicket, estado);

        Result<TicketDTO> resultDTO = new Result<>();
        resultDTO.correct = resultJPA.correct;
        resultDTO.message = resultJPA.message;
        resultDTO.ex = resultJPA.ex;

        if (!resultDTO.correct) {
            return ResponseEntity.badRequest().body(resultDTO);
        }

        if (resultJPA.object == null) {
            return ResponseEntity.noContent().build();
        }

        resultDTO.object = mapTicketJPAtoDTO((Ticket) resultJPA.object);

        return ResponseEntity.ok(resultDTO);
    }

    @PatchMapping("prioridad")
    public ResponseEntity<Result<TicketDTO>> updatePrioridad(@RequestParam int idTicket,
            @RequestParam int idPrioridad) {

        Result<Ticket> resultJPA = ticketsDAO.updatePrioridad(idTicket, idPrioridad);

        Result<TicketDTO> resultDTO = new Result<>();
        resultDTO.correct = resultJPA.correct;
        resultDTO.message = resultJPA.message;
        resultDTO.ex = resultJPA.ex;

        if (!resultDTO.correct) {
            return ResponseEntity.badRequest().body(resultDTO);
        }

        if (resultJPA.object == null) {
            return ResponseEntity.noContent().build();
        }

        resultDTO.object = mapTicketJPAtoDTO((Ticket) resultJPA.object);

        return ResponseEntity.ok(resultDTO);
    }

    public static UsuarioDTO mapUsuarioJPAToDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getApellidoPaterno(),
                usuario.getApellidoMaterno(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getCelular(),
                usuario.getActivo(),
                usuario.getRol());
    }

    public static TicketDTO mapTicketJPAtoDTO(Ticket ticket) {
        if (ticket == null) {
            return null;
        }

        TicketDTO ticketDTO = new TicketDTO(
                ticket.getIdTicket(),
                ticket.getTitulo(),
                ticket.getDescripcion(),
                ticket.getFechaCreacion(),
                ticket.getFechaActualizacion(),
                mapUsuarioJPAToDTO(ticket.getUsuarioSolicitante()),
                mapUsuarioJPAToDTO(ticket.getAgenteAsignado()),
                ticket.getPrioridad(),
                ticket.getEstado());

        ticketDTO.setStatus(ticket.getStatus());

        return ticketDTO;
    }
}
