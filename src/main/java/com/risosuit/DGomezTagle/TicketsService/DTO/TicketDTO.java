package com.risosuit.DGomezTagle.TicketsService.DTO;

import java.util.Date;

import com.risosuit.DGomezTagle.TicketsService.JPA.EstadoTicket;
import com.risosuit.DGomezTagle.TicketsService.JPA.Prioridad;

public class TicketDTO {

    private int idTicket;
    private String titulo;
    private String descripcion;
    private Date FechaCreacion;
    private Date FechaActualizacion;
    private UsuarioDTO usuarioSolicitante;
    private UsuarioDTO agenteAsignado;
    private Prioridad prioridad;
    private EstadoTicket estado;
    private int status;

    public TicketDTO() {
    }

    public TicketDTO(int idTicket, String titulo, String descripcion, Date FechaCreacion, Date FechaActualizacion,
            UsuarioDTO usuarioSolicitante, UsuarioDTO agenteAsignado, Prioridad prioridad, EstadoTicket estado) {
        this.idTicket = idTicket;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.FechaCreacion = FechaCreacion;
        this.FechaActualizacion = FechaActualizacion;
        this.usuarioSolicitante = usuarioSolicitante;
        this.agenteAsignado = agenteAsignado;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    public int getIdTicket() {
        return this.idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return this.FechaCreacion;
    }

    public void setFechaCreacion(Date FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }

    public Date getFechaActualizacion() {
        return this.FechaActualizacion;
    }

    public void setFechaActualizacion(Date FechaActualizacion) {
        this.FechaActualizacion = FechaActualizacion;
    }

    public UsuarioDTO getUsuarioSolicitante() {
        return this.usuarioSolicitante;
    }

    public void setUsuarioSolicitante(UsuarioDTO usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }

    public UsuarioDTO getAgenteAsignado() {
        return this.agenteAsignado;
    }

    public void setAgenteAsignado(UsuarioDTO agenteAsignado) {
        this.agenteAsignado = agenteAsignado;
    }

    public Prioridad getPrioridad() {
        return this.prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public EstadoTicket getEstado() {
        return this.estado;
    }

    public void setEstado(EstadoTicket estado) {
        this.estado = estado;
    }


    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
