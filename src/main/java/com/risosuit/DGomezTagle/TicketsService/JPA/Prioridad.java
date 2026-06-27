package com.risosuit.DGomezTagle.TicketsService.JPA;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
@Entity
@Table(name = "PRIORIDAD")
public class Prioridad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprioridad")
    private long idPrioridad;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "prioridad")
    private List<Ticket> tickets;


    public long getIdPrioridad() {
        return this.idPrioridad;
    }

    public void setIdPrioridad(long idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
