package com.risosuit.DGomezTagle.TicketsService.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.risosuit.DGomezTagle.TicketsService.DAO.RolDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Rol;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("Rol")
@CrossOrigin(origins = "http://localhost:4200")
public class RolRestController {

    private final RolDAOImplementation rolDAO;

    RolRestController(RolDAOImplementation rolDAO) {
        this.rolDAO = rolDAO;
    }

    @GetMapping
    public ResponseEntity<Result<Rol>> getAll() {
        Result<Rol> result = new Result<Rol>();
        try {
            result = rolDAO.getAllRol();
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
