package com.risosuit.DGomezTagle.TicketsService.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import com.risosuit.DGomezTagle.TicketsService.DAO.UsuarioDAOImplementation;
import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Usuario;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioDAOImplementation usuarioDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result result = usuarioDAO.getByUsername(username);

        if (!result.correct || result.object == null) {
            throw new UsernameNotFoundException("El usuario " + username + " no existe en el sistema.");
        }

        Usuario usuario = (Usuario) result.object;

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRol().getNombre())
                .disabled(usuario.getActivo() == 0)
                .build();
    }
}
