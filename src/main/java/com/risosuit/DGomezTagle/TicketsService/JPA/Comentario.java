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
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcomentario")
    private long idComentario;
    @ManyToOne
    @JoinColumn(name = "idticket")
    private Ticket ticket;
    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;
    @Column(name = "mensaje")
    private String mensaje;
    @Column(name = "fecha")
    private Date Fecha;

    public long getIdComentario() {
        return this.idComentario;
    }

    public void setIdComentario(long idComentario) {
        this.idComentario = idComentario;
    }

    public Ticket getTicket() {
        return this.ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return this.Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

}
