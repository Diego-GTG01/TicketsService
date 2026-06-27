package com.risosuit.DGomezTagle.TicketsService.JPA;

import jakarta.persistence.CascadeType;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "HISTORIAL")
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhistorial")
    private long idHistorial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idticket")
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idestadoanterior")
    private EstadoTicket estadoAnterior;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idestadoactual")
    private EstadoTicket estadoActual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    @Column(name = "fechaactualizacion")
    private Date fechaActualizacion;

    @Column(name = "descripcioncambio")
    private String descripcionCambio;

    public long getIdHistorial() {
        return this.idHistorial;
    }

    public void setIdHistorial(long idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Ticket getticket() {
        return this.ticket;
    }

    public void setticket(Ticket ticket) {
        this.ticket = ticket;
    }

    public EstadoTicket getEstadoAnterior() {
        return this.estadoAnterior;
    }

    public void setEstadoAnterior(EstadoTicket estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public EstadoTicket getEstadoActual() {
        return this.estadoActual;
    }

    public void setEstadoActual(EstadoTicket estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ticket getTicket() {
        return this.ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getDescripcionCambio() {
        return this.descripcionCambio;
    }

    public void setDescripcionCambio(String descripcionCambio) {
        this.descripcionCambio = descripcionCambio;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    

}
