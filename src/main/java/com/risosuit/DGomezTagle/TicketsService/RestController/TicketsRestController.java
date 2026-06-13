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
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/tickets")
public class TicketsRestController {

    private final TicketsDAOImplementation ticketsDAO;

    TicketsRestController(TicketsDAOImplementation ticketsDAO) {
        this.ticketsDAO = ticketsDAO;
    }

    @GetMapping("getAll")
    public ResponseEntity<Result<TicketDTO>> getAll() {
        Result<TicketDTO> resultDTO = new Result<TicketDTO>();
        try {
            Result<Ticket> resultJPA = ticketsDAO.getAll();

            resultDTO.correct = resultJPA.correct;
            resultDTO.message = resultJPA.message;

            if (resultDTO.correct) {
                if (resultJPA.objects.isEmpty()) {
                    return ResponseEntity.noContent().build();
                }
                resultDTO.objects = new ArrayList<>();
                for (Ticket ticket : resultJPA.objects) {
                    resultDTO.objects.add(mapTicketJPAtoDTO(ticket));
                }

                return ResponseEntity.ok(resultDTO);
            } else {
                return ResponseEntity.badRequest().body(resultDTO);
            }
        } catch (Exception ex) {
            resultDTO.correct = false;
            resultDTO.message = ex.getLocalizedMessage();
            resultDTO.ex = ex;
            return ResponseEntity.internalServerError().body(resultDTO);
        }
    }

    @GetMapping("")
    public ResponseEntity<Result<TicketDTO>> getByIdTicket(@RequestParam("idTicket") int idTicket) {
        Result<TicketDTO> resultDTO = new Result<TicketDTO>();
        try {
            Result<Ticket> resultJPA = ticketsDAO.getByIdTicket(idTicket);

            resultDTO.correct = resultJPA.correct;
            resultDTO.message = resultJPA.message;

            if (resultDTO.correct) {
                if (resultJPA.object == null) {
                    return ResponseEntity.noContent().build();
                }
                resultDTO.object = mapTicketJPAtoDTO((Ticket) resultJPA.object);

                return ResponseEntity.ok(resultDTO);
            } else {
                return ResponseEntity.badRequest().body(resultDTO);
            }
        } catch (Exception ex) {
            resultDTO.correct = false;
            resultDTO.message = ex.getLocalizedMessage();
            resultDTO.ex = ex;
            return ResponseEntity.internalServerError().body(resultDTO);
        }
    }

    public static UsuarioDTO mapUsuarioJPAToDTO(Usuario usuario) {
        if (usuario == null)
            return null;
        UsuarioDTO usuarioDTO = new UsuarioDTO(
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

        return usuarioDTO;
    }

    public static TicketDTO mapTicketJPAtoDTO(Ticket ticket) {
        if (ticket == null)
            return null;

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

        return ticketDTO;
    }
}