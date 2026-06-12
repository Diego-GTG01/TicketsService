package com.risosuit.DGomezTagle.TicketsService.JPA;

import java.util.Date;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idticket")
    private int idTicket;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fechacreacion")
    private Date FechaCreacion;
    @Column(name = "fechaactualizacion")
    private Date FechaActualizacion;
    @ManyToOne
    @JoinColumn(name = "idusuariosolicitante")
    private Usuario usuarioSolicitante;
    @ManyToOne
    @JoinColumn(name = "idagenteasignado")
    private Usuario agenteAsignado;
    @ManyToOne
    @JoinColumn(name = "idprioridad")
    private Prioridad prioridad;
    @ManyToOne
    @JoinColumn(name = "idestado")
    private EstadoTicket estado;

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

    public Usuario getUsuarioSolicitante() {
        return this.usuarioSolicitante;
    }

    public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }

    public Usuario getAgenteAsignado() {
        return this.agenteAsignado;
    }

    public void setAgenteAsignado(Usuario agenteAsignado) {
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

}
