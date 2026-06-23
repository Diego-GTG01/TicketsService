package com.risosuit.DGomezTagle.TicketsService.DAO;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Ticket;

public interface ITicket {
    Result<Ticket> getAll();

    Result<Ticket> getByIdTicket(int idTicket);

    Result<Ticket> getByIdUsuarioSolicitado(int idUsuarioSolicitado);

    Result<Ticket> getByIdUsuarioAgente(int idUsuarioAgente);

    // PENDIENTES
    Result<Ticket> addTicket(Ticket ticket);

    Result<Ticket> asignarTicket(int idTicket, int idAgente);

    Result<Ticket> updatePrioridad(int idTicket, int idPrioridad);

    Result<Ticket> updateEstado(int idTicket, int idEstado);
    
    Result<Ticket> updateStatus(int idTicket, int status);
    

}
