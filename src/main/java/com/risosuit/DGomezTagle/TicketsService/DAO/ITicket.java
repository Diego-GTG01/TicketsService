package com.risosuit.DGomezTagle.TicketsService.DAO;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Ticket;

public interface ITicket {
    Result<Ticket> getAll();
    Result<Ticket> getByIdTicket(int idTicket);
    Result<Ticket> getByIdUsuarioSolicitado(int idUsuarioSolicitado);
    Result<Ticket> getByIdUsuarioAgente(int idUsuarioAgente);

}
