package com.risosuit.DGomezTagle.TicketsService.DAO;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;

public interface IUsuario {
    Result getByUsername(String username);
    Result getAllAgentes();
    Result getByRol(String nombre);
}
