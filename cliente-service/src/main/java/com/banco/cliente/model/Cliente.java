package com.banco.cliente.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
public class Cliente {
    @Id
    @Column(name = "clienteid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long clienteId;
    @Column(name = "contrasena")
    private String contrasena;
    @Column(name = "estado")
    private Boolean estado = true;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;
}
