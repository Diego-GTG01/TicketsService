package com.risosuit.DGomezTagle.TicketsService.DAO;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Usuario;

public interface IUsuario {

    Result getByUsername(String username);

    Result getAllAgentes();

    Result getAll();

    Result getByRol(String nombre);

    Result addUsuario(Usuario usuario);

    Result updateUsuario(Usuario usuario);

    Result deleteUsuario(int idUsuario);

    Result updatePassword(int idUsuario, String password);

}
