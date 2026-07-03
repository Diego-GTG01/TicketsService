package com.risosuit.DGomezTagle.TicketsService.RestController;

import com.risosuit.DGomezTagle.TicketsService.DAO.VerificacionTokenDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.VerificacionToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class VerificarTokenRestController {

    @Autowired
    private VerificacionTokenDAOImplementation tokenDAO;

    @GetMapping
    public ResponseEntity<Result<VerificacionToken>> verificarToken(@RequestParam String token) {

        Result<VerificacionToken> result = tokenDAO.verifyToken(token);

        return result.correct
                ? ResponseEntity.ok(result)
                : ResponseEntity.badRequest().body(result);
    }

    @PostMapping
    public ResponseEntity<Result<VerificacionToken>> crearTokenValidacion(@RequestBody VerificacionToken token) {

        Result<VerificacionToken> result = tokenDAO.addToken(token);

        return result.correct
                ? ResponseEntity.ok(result)
                : ResponseEntity.badRequest().body(result);
    }
    
    @PostMapping("/recovery")
    public ResponseEntity<Result<VerificacionToken>> crearTokenRecuperacion(@RequestBody VerificacionToken token) {
        

        Result<VerificacionToken> result = tokenDAO.verifyTokenRecovery(token);

        return result.correct
                ? ResponseEntity.ok(result)
                : ResponseEntity.badRequest().body(result);
    }

}
