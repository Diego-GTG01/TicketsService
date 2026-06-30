package com.risosuit.DGomezTagle.TicketsService.RestController;

import com.risosuit.DGomezTagle.TicketsService.DAO.VerificacionTokenDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.VerificacionToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class VerificarTokenRestController {

    @Autowired
    private VerificacionTokenDAOImplementation tokenDAO;

    @GetMapping
    public ResponseEntity<Result<VerificacionToken>> verificarToken(
            @RequestParam("token") String token) {
        Result<VerificacionToken> result = new Result<VerificacionToken>();

        try {
            result = tokenDAO.verifyToken(token);
            if (result.correct) {
                return ResponseEntity.ok().body(result);
            } else {
                return ResponseEntity.badRequest().body(result);
            }

        } catch (Exception e) {
            result.correct = false;
            result.message = e.getLocalizedMessage();
            result.ex = e;
            return ResponseEntity.internalServerError().body(result);
        }
    }

    @PostMapping
    public ResponseEntity<Result<VerificacionToken>> crearToken(@RequestBody VerificacionToken token) {
        Result<VerificacionToken> result = new Result<VerificacionToken>();

        try {
            result = tokenDAO.addToken(token);
            if (result.correct) {
                return ResponseEntity.ok().body(result);
            } else {
                return ResponseEntity.badRequest().body(result);
            }

        } catch (Exception e) {
            result.correct = false;
            result.message = e.getLocalizedMessage();
            result.ex = e;
            return ResponseEntity.internalServerError().body(result);
        }
    }

}
