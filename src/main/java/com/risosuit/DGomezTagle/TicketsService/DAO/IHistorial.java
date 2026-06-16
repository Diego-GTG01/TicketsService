package com.risosuit.DGomezTagle.TicketsService.DAO;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.EstadoTicket;
import com.risosuit.DGomezTagle.TicketsService.JPA.Historial;

public interface IHistorial {
    Result <Historial> getHistorialByIdTicket(int idTicket);
    Result <Historial> updateEstadoTicket(EstadoTicket estadoTicket);
    Result <Historial> updatePrioridad(EstadoTicket estadoTicket);
    Result <Historial> liberarTicket(EstadoTicket estadoTicket);
    
    
}
