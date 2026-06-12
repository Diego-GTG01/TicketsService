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
    @Column(name = "idComentario")
    private long idComentario;
    @ManyToOne
    @JoinColumn(name = "idticket")
    private Ticket tiket;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    @Column(name = "name")
    private String mensaje;
    @Column(name = "descripcion")
    private Date Fecha;

    public long getIdComentario() {
        return this.idComentario;
    }

    public void setIdComentario(long idComentario) {
        this.idComentario = idComentario;
    }

    public Ticket getTiket() {
        return this.tiket;
    }

    public void setTiket(Ticket tiket) {
        this.tiket = tiket;
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
