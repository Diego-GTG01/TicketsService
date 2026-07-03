package com.risosuit.DGomezTagle.TicketsService.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.risosuit.DGomezTagle.TicketsService.DAO.UsuarioDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DAO.VerificacionTokenDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.DTO.UsuarioDTO;
import com.risosuit.DGomezTagle.TicketsService.JPA.Usuario;
import com.risosuit.DGomezTagle.TicketsService.Services.EmailService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/Usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioDAOImplementation usuarioDAO;

    @Autowired
    private VerificacionTokenDAOImplementation tokenDAO;

    @Autowired
    private EmailService emailService;

    @GetMapping("/byUsername")
    public ResponseEntity<Result<Usuario>> getByUsername(@RequestParam String username) {

        Result<Usuario> result = usuarioDAO.getByUsername(username);

        return result.correct
                ? ResponseEntity.ok(result)
                : ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/byRol")
    public ResponseEntity<Result<UsuarioDTO>> getByRol(@RequestParam String nombre) {

        Result<Usuario> result = usuarioDAO.getByRol(nombre);

        Result<UsuarioDTO> resultDTO = new Result<>();
        resultDTO.correct = result.correct;
        resultDTO.message = result.message;
        resultDTO.ex = result.ex;

        if (!resultDTO.correct) {
            return ResponseEntity.badRequest().body(resultDTO);
        }

        resultDTO.objects = new ArrayList<>();

        for (Usuario usuario : result.objects) {
            resultDTO.objects.add(mapUsuarioJPAToDTO(usuario));
        }

        return ResponseEntity.ok(resultDTO);
    }

    @GetMapping
    public ResponseEntity<Result<UsuarioDTO>> getAll() {

        Result<Usuario> result = usuarioDAO.getAll();

        Result<UsuarioDTO> resultDTO = new Result<>();
        resultDTO.correct = result.correct;
        resultDTO.message = result.message;
        resultDTO.ex = result.ex;

        if (!resultDTO.correct) {
            return ResponseEntity.badRequest().body(resultDTO);
        }

        resultDTO.objects = new ArrayList<>();

        for (Usuario usuario : result.objects) {
            resultDTO.objects.add(mapUsuarioJPAToDTO(usuario));
        }

        return ResponseEntity.ok(resultDTO);
    }

    @PostMapping
    public ResponseEntity<Result<UsuarioDTO>> addUser(@RequestBody Usuario usuario) {

        Result<Usuario> result = usuarioDAO.addUsuario(usuario);

        Result<UsuarioDTO> resultDTO = new Result<>();
        resultDTO.correct = result.correct;
        resultDTO.message = result.message;
        resultDTO.ex = result.ex;

        if (!resultDTO.correct) {
            return ResponseEntity.badRequest().body(resultDTO);
        }

        resultDTO.object = mapUsuarioJPAToDTO((Usuario) result.object);

        return ResponseEntity.ok(resultDTO);
    }

    @PutMapping
    public ResponseEntity<Result<UsuarioDTO>> updateUser(@RequestBody Usuario usuario) {

        Result<Usuario> result = usuarioDAO.updateUsuario(usuario);

        Result<UsuarioDTO> resultDTO = new Result<>();
        resultDTO.correct = result.correct;
        resultDTO.message = result.message;
        resultDTO.ex = result.ex;

        if (!resultDTO.correct) {
            return ResponseEntity.badRequest().body(resultDTO);
        }

        resultDTO.object = mapUsuarioJPAToDTO((Usuario) result.object);

        return ResponseEntity.ok(resultDTO);
    }

    @DeleteMapping
    public ResponseEntity<Result<UsuarioDTO>> deleteUser(@RequestParam int idUsuario) {

        Result<Usuario> result = usuarioDAO.deleteUsuario(idUsuario);

        Result<UsuarioDTO> resultDTO = new Result<>();
        resultDTO.correct = result.correct;
        resultDTO.message = result.message;
        resultDTO.ex = result.ex;

        if (!resultDTO.correct) {
            return ResponseEntity.badRequest().body(resultDTO);
        }

        resultDTO.object = mapUsuarioJPAToDTO((Usuario) result.object);

        return ResponseEntity.ok(resultDTO);
    }

    public static UsuarioDTO mapUsuarioJPAToDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getApellidoPaterno(),
                usuario.getApellidoMaterno(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getCelular(),
                usuario.getActivo(),
                usuario.getRol());
    }
}
