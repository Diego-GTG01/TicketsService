package com.risosuit.DGomezTagle.TicketsService.DAO;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Comentario;

public interface IComentario {
    Result<Comentario> getComentarioByIdTicket(long idTicket);
    Result<Comentario> addComentario(Comentario comentario);
}
