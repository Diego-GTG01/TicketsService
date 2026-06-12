package com.risosuit.DGomezTagle.TicketsService.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.risosuit.DGomezTagle.TicketsService.DAO.TicketsDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Ticket;

import jakarta.persistence.EntityManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/tickets")
public class TicketsRestController {


    private final TicketsDAOImplementation ticketsDAO;

    TicketsRestController(TicketsDAOImplementation ticketsDAO) {
        this.ticketsDAO = ticketsDAO;
    }

    @GetMapping
    public ResponseEntity<Result<Ticket>> getAll() {
        Result<Ticket> result = new Result<Ticket>();
        try {
            result = ticketsDAO.getAll();
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            result.correct = false;
            result.message = ex.getLocalizedMessage();
            result.ex = ex;
            return ResponseEntity.internalServerError().body(result);
        }

    }

}
