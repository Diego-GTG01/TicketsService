package com.risosuit.DGomezTagle.TicketsService.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.risosuit.DGomezTagle.TicketsService.DAO.ComentarioDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DAO.HistorialDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Comentario;

@RestController
@RequestMapping("Comentario")
public class ComentarioRestController {
    @Autowired
    private ComentarioDAOImplementation comentarioDAO;

    @Autowired
    private HistorialDAOImplementation historialDAO;

    @GetMapping
    public ResponseEntity<Result<Comentario>> getAll(@RequestParam long idTicket) {

        Result<Comentario> result = comentarioDAO.getComentarioByIdTicket(idTicket);

        return result.correct
                ? ResponseEntity.ok(result)
                : ResponseEntity.badRequest().body(result);
    }

    @PostMapping
    public ResponseEntity<Result<Comentario>> addComentario(@RequestBody Comentario comentario) {

        Result<Comentario> result = comentarioDAO.addComentario(comentario);

        return result.correct
                ? ResponseEntity.ok(result)
                : ResponseEntity.badRequest().body(result);
    }
}