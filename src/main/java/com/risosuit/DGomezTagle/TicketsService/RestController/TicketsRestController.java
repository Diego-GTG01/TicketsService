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

    @GetMapping("ByUsuarioSolicitado")
    public ResponseEntity<Result<TicketDTO>> getByIdUsuarioSolicitado(@RequestParam("idUsuario") int idUsuario) {
        Result<TicketDTO> resultDTO = new Result<TicketDTO>();
        try {
            Result<Ticket> resultJPA = ticketsDAO.getByIdUsuarioSolicitado(idUsuario);

            resultDTO.correct = resultJPA.correct;
            resultDTO.message = resultJPA.message;

            if (resultDTO.correct) {
                if (resultJPA.objects.isEmpty()) {
                    return ResponseEntity.noContent().build();
                } else {
                    resultDTO.objects = new ArrayList<>();
                    for (Ticket ticket : resultJPA.objects) {
                        resultDTO.objects.add(mapTicketJPAtoDTO(ticket));
                    }
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

    @GetMapping("ByAgenteAsignado")
    public ResponseEntity<Result<TicketDTO>> getByIdUsuarioAgente(@RequestParam("idUsuario") int idUsuario) {
        Result<TicketDTO> resultDTO = new Result<TicketDTO>();
        try {
            Result<Ticket> resultJPA = ticketsDAO.getByIdUsuarioAgente(idUsuario);

            resultDTO.correct = resultJPA.correct;
            resultDTO.message = resultJPA.message;

            if (resultDTO.correct) {
                if (resultJPA.objects.isEmpty()) {
                    return ResponseEntity.noContent().build();
                } else {
                    resultDTO.objects = new ArrayList<>();
                    for (Ticket ticket : resultJPA.objects) {
                        resultDTO.objects.add(mapTicketJPAtoDTO(ticket));
                    }
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

    @PostMapping
    public ResponseEntity<Result<TicketDTO>> addTicket(@RequestBody Ticket ticket) {
        Result<TicketDTO> resultDTO = new Result<TicketDTO>();
        try {
            Result<Ticket> resultJPA = ticketsDAO.addTicket(ticket);

            resultDTO.correct = resultJPA.correct;
            resultDTO.message = resultJPA.message;
            resultDTO.ex = resultJPA.ex;
            if (resultDTO.correct) {
                if (resultJPA.object == null) {
                    return ResponseEntity.noContent().build();
                } else {
                    resultDTO.object = mapTicketJPAtoDTO(ticket);
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

    @PatchMapping("assignTo")
    public ResponseEntity<Result<TicketDTO>> asignarTicket(@RequestParam("idTicket") int idTicket,
            @RequestParam("idAgenteAsignado") int idAgenteAsignado) {
        Result<TicketDTO> resultDTO = new Result<TicketDTO>();
        try {
            Result<Ticket> resultJPA = ticketsDAO.asignarTicket(idTicket, idAgenteAsignado);

            resultDTO.correct = resultJPA.correct;
            resultDTO.message = resultJPA.message;
            resultDTO.ex = resultJPA.ex;
            if (resultDTO.correct) {
                if (resultJPA.object == null) {
                    return ResponseEntity.noContent().build();
                } else {
                    resultDTO.object = mapTicketJPAtoDTO((Ticket) resultJPA.object);
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

    @PatchMapping("status")
    public ResponseEntity<Result<TicketDTO>> updateStatus(@RequestParam("idTicket") int idTicket,
            @RequestParam("status") int status) {
        Result<TicketDTO> resultDTO = new Result<TicketDTO>();
        try {
            Result<Ticket> resultJPA = ticketsDAO.updateStatus(idTicket, status);

            resultDTO.correct = resultJPA.correct;
            resultDTO.message = resultJPA.message;
            resultDTO.ex = resultJPA.ex;
            if (resultDTO.correct) {
                if (resultJPA.object == null) {
                    return ResponseEntity.noContent().build();
                } else {
                    resultDTO.object = mapTicketJPAtoDTO((Ticket) resultJPA.object);
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

    @PatchMapping("estado")
    public ResponseEntity<Result<TicketDTO>> updateEstado(@RequestParam("idTicket") int idTicket,
            @RequestParam("estado") int estado) {
        Result<TicketDTO> resultDTO = new Result<TicketDTO>();
        try {
            Result<Ticket> resultJPA = ticketsDAO.updateEstado(idTicket, estado);

            resultDTO.correct = resultJPA.correct;
            resultDTO.message = resultJPA.message;
            resultDTO.ex = resultJPA.ex;
            if (resultDTO.correct) {
                if (resultJPA.object == null) {
                    return ResponseEntity.noContent().build();
                } else {
                    resultDTO.object = mapTicketJPAtoDTO((Ticket) resultJPA.object);
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
    
    
    @PatchMapping("prioridad")
    public ResponseEntity<Result<TicketDTO>> updatePrioridad(@RequestParam("idTicket") int idTicket,
            @RequestParam("idPrioridad") int idPrioridad) {
        Result<TicketDTO> resultDTO = new Result<TicketDTO>();
        try {
            Result<Ticket> resultJPA = ticketsDAO.updatePrioridad(idTicket, idPrioridad);

            resultDTO.correct = resultJPA.correct;
            resultDTO.message = resultJPA.message;
            resultDTO.ex = resultJPA.ex;
            if (resultDTO.correct) {
                if (resultJPA.object == null) {
                    return ResponseEntity.noContent().build();
                } else {
                    resultDTO.object = mapTicketJPAtoDTO((Ticket) resultJPA.object);
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

    public static UsuarioDTO mapUsuarioJPAToDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
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
