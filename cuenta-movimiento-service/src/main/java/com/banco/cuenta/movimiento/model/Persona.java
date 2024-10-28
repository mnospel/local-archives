package com.banco.cuenta.movimiento.model;

import lombok.Data;

@Data
public class Persona {
    private Long personaId;
    private String nombre;
    private String genero;
    private String edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
