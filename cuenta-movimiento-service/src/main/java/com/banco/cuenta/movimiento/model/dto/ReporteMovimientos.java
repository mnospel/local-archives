package com.banco.cuenta.movimiento.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReporteMovimientos {
    private LocalDateTime fechaMovimiento;
    private String nombreCliente;
    private String numeroCuenta;
    private String tipoMovimiento;
    private double saldoMovimiento;
    private boolean estadoCuenta;
    private double valorMovimiento;
    private double saldoDisponible;
}
