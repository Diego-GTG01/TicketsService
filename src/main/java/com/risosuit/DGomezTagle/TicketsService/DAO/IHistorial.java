package com.risosuit.DGomezTagle.TicketsService.DAO;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.EstadoTicket;
import com.risosuit.DGomezTagle.TicketsService.JPA.Historial;

public interface IHistorial {
    Result <Historial> getHistorialByIdTicket(int idTicket);
    Result <Historial> updateEstadoTicket(Historial historial);
    Result <Historial> updatePrioridad(Historial historial);
    Result <Historial> liberarTicket(EstadoTicket estadoTicket);
    
    
}
