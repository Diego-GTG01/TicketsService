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
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    @Autowired
    @Lazy
    private UsuarioDAOImplementation usuarioDAO;

    @Transactional
    @Override
    public Result<VerificacionToken> addToken(VerificacionToken token) {
        Result<VerificacionToken> result = new Result<>();
        try {

            if (token.getUsuarioToken() == null) {
                result.correct = false;
                result.message = "Usuario no encontrado";
                return result;
            }
            Usuario usuario;
            if (token.getTipo() == 1) {
                usuario = (Usuario) usuarioDAO.getByUsername(token.getUsuarioToken().getUsername()).object;

            } else {
                usuario = entityManager.find(
                        Usuario.class,
                        token.getUsuarioToken().getIdUsuario());
            }

            if (usuario == null) {
                result.correct = false;
                result.message = "Usuario no encontrado";
                return result;
            }

            if (token.getTipo() == 0 && usuario.getActivo() == 1) {
                result.correct = false;
                result.message = "Usuario ya verificado";
                return result;
            }

            TypedQuery<VerificacionToken> query = entityManager.createQuery(
                    "SELECT t FROM VerificacionToken t WHERE t.usuarioToken.idUsuario = :idUsuario",
                    VerificacionToken.class);

            query.setParameter("idUsuario", usuario.getIdUsuario());

            List<VerificacionToken> tokens = query.getResultList();

            if (!tokens.isEmpty()) {

                VerificacionToken tokenExistente = tokens.get(0);

                if (tokenExistente.getFechaExpiracion().isAfter(LocalDateTime.now())) {

                    result.correct = false;
                    result.message = tokenExistente.getTipo() == 0
                            ? "El usuario ya tiene un token de verificación vigente."
                            : "El usuario ya tiene un token de recuperación vigente.";

                    result.object = tokenExistente;
                    return result;
                }

                entityManager.remove(tokenExistente);
            }

            token.setUsuarioToken(usuario);
            token.setToken(UUID.randomUUID().toString().replace("-", ""));
            token.setFechaExpiracion(LocalDateTime.now().plusMinutes(15));

            entityManager.persist(token);

            if (token.getTipo() == 0) {

                emailService.enviarCorreoVerificacion(
                        usuario.getEmail(),
                        token.getToken());

            } else {

                emailService.enviarCorreoRecuperacion(
                        usuario.getEmail(),
                        token.getToken());
            }

            result.correct = true;
            result.message = "Token generado correctamente.";
            result.object = token;

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
            if (tokenBD.getTipo() == 1) {
                result.correct = true;
                result.message = "El token es valido.";
                return result;

            } else {
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

    @Transactional
    @Override
    public Result<VerificacionToken> verifyTokenRecovery(VerificacionToken token) {
        Result<VerificacionToken> result = new Result<>();
        try {
            if (token == null || token.getToken() == null) {
                result.correct = false;
                result.message = "El token enviado no es válido.";
                return result;
            }

            if (token.getTipo() < 1) {
                result.correct = false;
                result.message = "El tipo de token no es válido.";
                return result;
            }

            if (token.getUsuarioToken() == null || token.getUsuarioToken().getPassword() == null) {
                result.correct = false;
                result.message = "La nueva contraseña es requerida.";
                return result;
            }

            VerificacionToken tokenBD = entityManager.createQuery(
                    "SELECT t FROM VerificacionToken t WHERE t.token = :tokenStr", VerificacionToken.class)
                    .setParameter("tokenStr", token.getToken())
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

                usuario.setPassword(token.getUsuarioToken().getPassword());
                entityManager.merge(usuario);

                entityManager.remove(tokenBD);

                result.correct = true;
                result.message = "Contraseña actualizada con éxito.";
                result.object = tokenBD;
            } else {
                result.correct = false;
                result.message = "No se encontró un usuario asociado a este token.";
            }

        } catch (Exception e) {
            result.correct = false;
            result.message = "Ocurrió un error al procesar la solicitud.";
            result.ex = e; 
        }
        return result;
    }

}
