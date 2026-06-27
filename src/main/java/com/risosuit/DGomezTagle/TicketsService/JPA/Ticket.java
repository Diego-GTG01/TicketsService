package com.risosuit.DGomezTagle.TicketsService.JPA;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import java.util.Date;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "TICKET")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idticket")
    private long idTicket;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fechacreacion")
    private Date fechaCreacion;

    @Column(name = "fechaactualizacion")
    private Date fechaActualizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuariosolicitante")
    private Usuario usuarioSolicitante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idagenteasignado")
    private Usuario agenteAsignado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idprioridad")
    private Prioridad prioridad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idestado")
    private EstadoTicket estado;

    @Column(name = "status")
    private int status;
    @JsonIgnore
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;
    @JsonIgnore
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Historial> historial;

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Historial> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Historial> historial) {
        this.historial = historial;
    }

    public long getIdTicket() {
        return this.idTicket;
    }

    public void setIdTicket(long idTicket) {
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
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date FechaCreacion) {
        this.fechaCreacion = FechaCreacion;
    }

    public Date getFechaActualizacion() {
        return this.fechaActualizacion;
    }

    public void setFechaActualizacion(Date FechaActualizacion) {
        this.fechaActualizacion = FechaActualizacion;
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

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
