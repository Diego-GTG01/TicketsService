package com.risosuit.DGomezTagle.TicketsService.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import com.risosuit.DGomezTagle.TicketsService.DAO.UsuarioDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.LoginRequest;
import com.risosuit.DGomezTagle.TicketsService.DTO.LoginResponse;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Usuario;
import com.risosuit.DGomezTagle.TicketsService.Services.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioDAOImplementation usuarioDAO;

    @PostMapping("/login")
    public ResponseEntity<Result<LoginResponse>> login(
            @RequestBody LoginRequest request) {

        Result<LoginResponse> result = new Result<>();

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()));

            String token = jwtService.generateToken(
                    authentication.getName());
            String username = authentication.getName();
            String rol = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .filter(auth -> auth.startsWith("ROLE_"))
                    .map(auth -> auth.replace("ROLE_", ""))
                    .findFirst()
                    .orElse("");
            Result resultUsuario = usuarioDAO.getByUsername(username);
            if (resultUsuario.correct) {

                Usuario usuario = (Usuario) resultUsuario.object;
                result.correct = true;
                result.message = "Login exitoso";
                result.object = new LoginResponse(token, username, rol,(long) usuario.getIdUsuario());

                return ResponseEntity.ok(result);
            }else{
                result.correct = true;
                result.message = "Login error, usuario no encontrado";

                return ResponseEntity.ok(result);
            }

        } catch (Exception ex) {

            result.correct = false;
            result.message = "Usuario o contraseña incorrectos";
            result.ex = ex;

            return ResponseEntity.badRequest().body(result);
        }
    }
}