package com.risosuit.DGomezTagle.TicketsService.DAO;

import com.risosuit.DGomezTagle.TicketsService.DTO.Result;
import com.risosuit.DGomezTagle.TicketsService.JPA.EstadoTicket;
import com.risosuit.DGomezTagle.TicketsService.JPA.Historial;

public class HistorialDAOImplementation implements IHistorial{

    @Override
    public Result<Historial> getHistorialByIdTicket(int idTicket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHistorialByIdTicket'");
    }

    @Override
    public Result<Historial> updateEstadoTicket(EstadoTicket estadoTicket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEstadoTicket'");
    }

    @Override
    public Result<Historial> updatePrioridad(EstadoTicket estadoTicket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePrioridad'");
    }

    @Override
    public Result<Historial> liberarTicket(EstadoTicket estadoTicket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'liberarTicket'");
    }

}
