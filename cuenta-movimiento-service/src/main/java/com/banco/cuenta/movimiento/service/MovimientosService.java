package com.banco.cuenta.movimiento.service;

import com.banco.cuenta.movimiento.model.Movimientos;
import com.banco.cuenta.movimiento.model.dto.ReporteMovimientos;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientosService {
    Movimientos registrarMovimiento(String numeroCuenta, Movimientos movimiento);
    List<Movimientos> obtenerMovimientosPorCuenta(String numeroCuenta);
    List<ReporteMovimientos> generarReporte(LocalDateTime fechaInicio, LocalDateTime fechaFin, Long clienteId);
}
