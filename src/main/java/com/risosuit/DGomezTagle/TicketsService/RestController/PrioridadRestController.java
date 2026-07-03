package com.risosuit.DGomezTagle.TicketsService.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.risosuit.DGomezTagle.TicketsService.DAO.PrioridadDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Prioridad;

@RestController
@RequestMapping("Prioridad")
public class PrioridadRestController {

    @Autowired
    private PrioridadDAOImplementation prioridadDAO;

    @GetMapping
    public ResponseEntity<Result<Prioridad>> getAll() {

        Result<Prioridad> result = prioridadDAO.getAll();

        return result.correct
                ? ResponseEntity.ok(result)
                : ResponseEntity.badRequest().body(result);
    }

}