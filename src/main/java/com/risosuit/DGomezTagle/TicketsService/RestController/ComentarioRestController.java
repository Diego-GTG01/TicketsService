package com.risosuit.DGomezTagle.TicketsService.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.risosuit.DGomezTagle.TicketsService.DAO.ComentarioDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DAO.HistorialDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Comentario;
import com.risosuit.DGomezTagle.TicketsService.JPA.Historial;

@RestController
@RequestMapping("Comentario")
public class ComentarioRestController {
    @Autowired
    private ComentarioDAOImplementation comentarioDAO;

    @Autowired
    private HistorialDAOImplementation historialDAO;

    @GetMapping
    public ResponseEntity<Result<Comentario>> getAll(@RequestParam("idTicket") long idTicket) {
        Result<Comentario> result = new Result<Comentario>();
        try {
            result = comentarioDAO.getComentarioByIdTicket(idTicket);
            if (result.correct) {
                return ResponseEntity.ok().body(result);

            } else {
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
            return ResponseEntity.internalServerError().body(result);
        }

    }

    @PostMapping
    public ResponseEntity<Result<Comentario>> addComentario(@RequestBody Comentario Comentario) {
        Result<Comentario> result = new Result<Comentario>();

        try {
            result = comentarioDAO.addComentario(Comentario);
            if (result.correct) {

                return ResponseEntity.ok().body(result);
            } else {

                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
            return ResponseEntity.internalServerError().body(result);
        }

    }

}
