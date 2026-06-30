package com.risosuit.DGomezTagle.TicketsService.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.risosuit.DGomezTagle.TicketsService.DAO.UsuarioDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DAO.VerificacionTokenDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.DTO.UsuarioDTO;
import com.risosuit.DGomezTagle.TicketsService.JPA.Usuario;
import com.risosuit.DGomezTagle.TicketsService.Services.EmailService;

import jakarta.annotation.Generated;

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
    public ResponseEntity getByUsername(@RequestParam String username) {
        Result<Usuario> result = new Result();

        try {
            result = usuarioDAO.getByUsername(username);
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

    @GetMapping("/byRol")
    public ResponseEntity<Result<UsuarioDTO>> getByRol(@RequestParam("nombre") String nombre) {
        Result<Usuario> result = new Result<Usuario>();
        Result<UsuarioDTO> resultDTO = new Result<UsuarioDTO>();
        try {
            result = usuarioDAO.getByRol(nombre);
            resultDTO.correct = result.correct;
            resultDTO.message = result.message;
            resultDTO.object = result.object;
            resultDTO.objects = new ArrayList<UsuarioDTO>();
            resultDTO.ex = result.ex;

            if (result.correct) {
                for (Usuario usuario : result.objects) {
                    resultDTO.objects.add(mapUsuarioJPAToDTO(usuario));
                }
                return ResponseEntity.ok().body(resultDTO);
            } else {
                return ResponseEntity.badRequest().body(resultDTO);
            }
        } catch (Exception ex) {
            resultDTO.correct = false;
            resultDTO.message = ex.getLocalizedMessage();
            resultDTO.ex = ex;
            return ResponseEntity.internalServerError().body(resultDTO);
        }

    }

    @GetMapping
    public ResponseEntity<Result<UsuarioDTO>> getAll() {
        Result<Usuario> result = new Result<Usuario>();
        Result<UsuarioDTO> resultDTO = new Result<UsuarioDTO>();
        try {
            result = usuarioDAO.getAll();
            resultDTO.correct = result.correct;
            resultDTO.message = result.message;
            resultDTO.object = result.object;
            resultDTO.objects = new ArrayList<UsuarioDTO>();
            resultDTO.ex = result.ex;

            if (result.correct) {
                for (Usuario usuario : result.objects) {
                    resultDTO.objects.add(mapUsuarioJPAToDTO(usuario));
                }
                return ResponseEntity.ok().body(resultDTO);
            } else {
                return ResponseEntity.badRequest().body(resultDTO);
            }
        } catch (Exception ex) {
            resultDTO.correct = false;
            resultDTO.message = ex.getLocalizedMessage();
            resultDTO.ex = ex;
            return ResponseEntity.internalServerError().body(resultDTO);
        }

    }

    @PostMapping
    public ResponseEntity<Result<UsuarioDTO>> addUser(@RequestBody Usuario usuario) {
        Result<Usuario> result = new Result<Usuario>();
        Result<UsuarioDTO> resultDTO = new Result<UsuarioDTO>();
        try {
            result = usuarioDAO.addUsuario(usuario);
            resultDTO.correct = result.correct;
            resultDTO.message = result.message;
            resultDTO.ex = result.ex;

            if (resultDTO.correct) {
                resultDTO.object = mapUsuarioJPAToDTO((Usuario) result.object);
                

                return ResponseEntity.ok().body(resultDTO);
            } else {
                return ResponseEntity.badRequest().body(resultDTO);
            }
        } catch (Exception ex) {
            resultDTO.correct = false;
            resultDTO.message = ex.getLocalizedMessage();
            resultDTO.ex = ex;
            return ResponseEntity.internalServerError().body(resultDTO);
        }

    }

    @PutMapping
    public ResponseEntity<Result<UsuarioDTO>> updateUser(@RequestBody Usuario usuario) {
        Result<Usuario> result = new Result<Usuario>();
        Result<UsuarioDTO> resultDTO = new Result<UsuarioDTO>();
        try {
            result = usuarioDAO.updateUsuario(usuario);
            resultDTO.correct = result.correct;
            resultDTO.message = result.message;
            resultDTO.ex = result.ex;

            if (resultDTO.correct) {
                resultDTO.object = mapUsuarioJPAToDTO((Usuario) result.object);
                return ResponseEntity.ok().body(resultDTO);
            } else {
                return ResponseEntity.badRequest().body(resultDTO);
            }
        } catch (Exception ex) {
            resultDTO.correct = false;
            resultDTO.message = ex.getLocalizedMessage();
            resultDTO.ex = ex;
            return ResponseEntity.internalServerError().body(resultDTO);
        }

    }

    @DeleteMapping
    public ResponseEntity<Result<UsuarioDTO>> deleteUser(@RequestParam("idUsuario") int idUsuario) {
        Result<Usuario> result = new Result<Usuario>();
        Result<UsuarioDTO> resultDTO = new Result<UsuarioDTO>();
        try {
            result = usuarioDAO.deleteUsuario(idUsuario);
            resultDTO.correct = result.correct;
            resultDTO.message = result.message;
            resultDTO.ex = result.ex;

            if (resultDTO.correct) {
                resultDTO.object = mapUsuarioJPAToDTO((Usuario) result.object);
                return ResponseEntity.ok().body(resultDTO);
            } else {
                return ResponseEntity.badRequest().body(resultDTO);
            }
        } catch (Exception ex) {
            resultDTO.correct = false;
            resultDTO.message = ex.getLocalizedMessage();
            resultDTO.ex = ex;
            return ResponseEntity.internalServerError().body(resultDTO);
        }

    }

    public static UsuarioDTO mapUsuarioJPAToDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO usuarioDTO = new UsuarioDTO(
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

        return usuarioDTO;
    }

}
