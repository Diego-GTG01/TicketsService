package com.risosuit.DGomezTagle.TicketsService.JPA;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idhistorial")
    private int idHistorial;
    @ManyToOne
    @JoinColumn(name= "idticket")
    private Ticket tiket;
    @ManyToOne
    @JoinColumn(name="idestadoanterior")
    private EstadoTicket estadoAnterior;
    @ManyToOne
    @JoinColumn(name="idestadoactual")
    private EstadoTicket estadoActual;
    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;
    @Column(name="fechaactualizacion")
    private Date fechaActualizaciion;

    public int getIdHistorial() {
        return this.idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Ticket getTiket() {
        return this.tiket;
    }

    public void setTiket(Ticket tiket) {
        this.tiket = tiket;
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

    public Date getFechaActualizaciion() {
        return this.fechaActualizaciion;
    }

    public void setFechaActualizaciion(Date fechaActualizaciion) {
        this.fechaActualizaciion = fechaActualizaciion;
    }

}
