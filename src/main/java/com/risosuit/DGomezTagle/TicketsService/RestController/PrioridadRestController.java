package com.risosuit.DGomezTagle.TicketsService.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.risosuit.DGomezTagle.TicketsService.DAO.PrioridadDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Prioridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("Prioridad")
public class PrioridadRestController {

    @Autowired
    private PrioridadDAOImplementation prioridadDAO;

    @GetMapping
    public ResponseEntity<Result<Prioridad>> getAll() {
        Result<Prioridad> result = new Result<Prioridad>();
        try {
            result = prioridadDAO.getAll();
            if (result.correct) {
                return ResponseEntity.ok().body(result);

            }else{
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
