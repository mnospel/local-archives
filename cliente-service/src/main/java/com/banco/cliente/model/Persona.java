package com.banco.cliente.model;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Persona {
    @Id
    @Column(name = "id_persona")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long personaId;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "genero")
    private String genero;
    @Column(name = "edad")
    private String edad;
    @Column(name = "identificacion")
    private String identificacion;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
}
