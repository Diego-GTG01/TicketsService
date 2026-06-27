package com.risosuit.DGomezTagle.TicketsService.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="ESTADOTICKET")
public class EstadoTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestado")
    private long idEstado;
    @Column(name = "nombre")
    private String nombre;

    public long getIdEstado() {
        return this.idEstado;
    }

    public void setIdEstado(long idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
