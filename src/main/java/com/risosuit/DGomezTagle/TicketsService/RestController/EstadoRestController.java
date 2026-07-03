package com.risosuit.DGomezTagle.TicketsService.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.risosuit.DGomezTagle.TicketsService.DAO.EstadoDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.EstadoTicket;

@RestController
@RequestMapping("Estado")
public class EstadoRestController {

    @Autowired
    private EstadoDAOImplementation estadoDAO;

    @GetMapping
    public ResponseEntity<Result<EstadoTicket>> getAll() {

        Result<EstadoTicket> result = estadoDAO.getAll();

        return result.correct
                ? ResponseEntity.ok(result)
                : ResponseEntity.badRequest().body(result);
    }

}