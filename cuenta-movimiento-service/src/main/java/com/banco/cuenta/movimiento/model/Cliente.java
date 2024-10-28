package com.banco.cuenta.movimiento.model;

import lombok.Data;

@Data
public class Cliente {
    private Long clienteId;
    private String contrasena;
    private Boolean estado = true;
    private Persona persona;
}
