package com.risosuit.DGomezTagle.TicketsService.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.risosuit.DGomezTagle.TicketsService.DAO.HistorialDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Historial;

@RestController
@RequestMapping("Historial")
public class HistorialRestController {

    @Autowired
    private HistorialDAOImplementation historialDAO;

    @GetMapping
    public ResponseEntity<Result<Historial>> getHistorialByIdTicket(@RequestParam int idTicket) {

        Result<Historial> result = historialDAO.getHistorialByIdTicket(idTicket);

        return result.correct
                ? ResponseEntity.ok(result)
                : ResponseEntity.badRequest().body(result);
    }

    @PostMapping
    public ResponseEntity<Result<Historial>> addHistorial(@RequestBody Historial historial) {

        Result<Historial> result = historialDAO.updateEstadoTicket(historial);

        return result.correct
                ? ResponseEntity.ok(result)
                : ResponseEntity.badRequest().body(result);
    }

}