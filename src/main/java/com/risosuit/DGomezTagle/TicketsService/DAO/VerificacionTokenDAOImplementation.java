/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.risosuit.DGomezTagle.TicketsService.DAO;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Usuario;
import com.risosuit.DGomezTagle.TicketsService.JPA.VerificacionToken;
import com.risosuit.DGomezTagle.TicketsService.Services.EmailService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ALIEN62
 */
@Repository
public class VerificacionTokenDAOImplementation implements IVerificacionToken {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EmailService emailService;

    @Transactional
    @Override
    public Result<VerificacionToken> addToken(VerificacionToken token) {
        Result<VerificacionToken> result = new Result<VerificacionToken>();
        try {
            if (token.getUsuarioToken() != null) {
                Usuario usuario = entityManager.find(Usuario.class, token.getUsuarioToken().getIdUsuario());
                if (usuario == null) {
                    result.correct = false;
                    result.message = "Usuario no encontrado";

                } else if (usuario.getActivo() != 1) {
                    String cadenaAleatoria = UUID.randomUUID().toString().replace("-", "");
                    token.setToken(cadenaAleatoria);
                    LocalDateTime ahora = LocalDateTime.now();
                    LocalDateTime expiracion = ahora.plusMinutes(15);
                    token.setFechaExpiracion(expiracion);
                    entityManager.persist(token);
                    result.correct = true;
                    result.message = "Token Guardado";
                    result.object = token;
                    emailService.enviarCorreoVerificacion(usuario.getEmail(), token.getToken());
                } else {
                    result.correct = false;
                    result.message = "Usuario Ya verificado";
                }

            } else {
                result.correct = false;
                result.message = "Usuario no encontrado";

            }
        } catch (Exception e) {
            result.correct = false;
            result.message = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }

    @Transactional
    @Override
    public Result<VerificacionToken> verifyToken(String token) {
        Result<VerificacionToken> result = new Result<VerificacionToken>();
        try {

            VerificacionToken tokenBD = entityManager.createQuery(
                    "SELECT t FROM VerificacionToken t WHERE t.token = :tokenStr", VerificacionToken.class)
                    .setParameter("tokenStr", token)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (tokenBD == null) {
                result.correct = false;
                result.message = "El token no es válido o no existe.";
                return result;
            }

            if (tokenBD.getFechaExpiracion().isBefore(LocalDateTime.now())) {
                result.correct = false;
                result.message = "El token ha expirado.";
                return result;
            }

            Usuario usuario = tokenBD.getUsuarioToken();
            if (usuario != null) {
                usuario.setActivo(1);
                entityManager.merge(usuario);

                entityManager.remove(tokenBD);

                result.correct = true;
                result.message = "Cuenta verificada con éxito.";
                result.object = tokenBD;
            } else {
                result.correct = false;
                result.message = "No se encontró un usuario asociado a este token.";
            }

        } catch (Exception e) {
            result.correct = false;
            result.message = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }

    @Override
    public Result<VerificacionToken> deleteToken(VerificacionToken token) {
        Result<VerificacionToken> result = new Result<VerificacionToken>();
        try {
            VerificacionToken tokenAManejar = entityManager.find(VerificacionToken.class, token.getIdToken());

            if (tokenAManejar != null) {
                entityManager.remove(tokenAManejar);
                result.correct = true;
                result.message = "Token eliminado correctamente.";
            } else {
                result.correct = false;
                result.message = "El token no existe o ya fue eliminado.";
            }
        } catch (Exception e) {
            result.correct = false;
            result.message = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }

}
