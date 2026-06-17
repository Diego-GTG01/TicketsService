package com.risosuit.DGomezTagle.TicketsService.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.risosuit.DGomezTagle.TicketsService.DAO.HistorialDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.EstadoTicket;
import com.risosuit.DGomezTagle.TicketsService.JPA.Historial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("Historial")
public class HistorialRestController {

    @Autowired
    private HistorialDAOImplementation historialDAO;

    @GetMapping()
    public ResponseEntity<Result<Historial>> getHistorialByIdTicket(@RequestParam("idTicket") int idTicket) {
        Result<Historial> result = new Result<Historial>();
        try {
            result = historialDAO.getHistorialByIdTicket(idTicket);
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
