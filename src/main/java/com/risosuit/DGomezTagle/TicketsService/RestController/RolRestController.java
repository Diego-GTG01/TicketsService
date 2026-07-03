package com.risosuit.DGomezTagle.TicketsService.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.risosuit.DGomezTagle.TicketsService.DAO.RolDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Rol;

@RestController
@RequestMapping("Rol")
public class RolRestController {

    private final RolDAOImplementation rolDAO;

    public RolRestController(RolDAOImplementation rolDAO) {
        this.rolDAO = rolDAO;
    }

    @GetMapping
    public ResponseEntity<Result<Rol>> getAll() {

        Result<Rol> result = rolDAO.getAllRol();

        return result.correct
                ? ResponseEntity.ok(result)
                : ResponseEntity.badRequest().body(result);
    }
}