package com.risosuit.DGomezTagle.TicketsService.DAO;

import java.util.List;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.Ticket;

public interface ITicket {
    Result<Ticket> getAll();

}
