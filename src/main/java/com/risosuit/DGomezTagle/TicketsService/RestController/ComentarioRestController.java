package com.risosuit.DGomezTagle.TicketsService.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.risosuit.DGomezTagle.TicketsService.DAO.ComentarioDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Comentario;

@RestController
@RequestMapping("Comentario")
public class ComentarioRestController {
    @Autowired
    private ComentarioDAOImplementation comentarioDAO;

    @GetMapping
    public ResponseEntity<Result<Comentario>> getAll(@RequestParam("idTicket") int idTicket) {
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

}
