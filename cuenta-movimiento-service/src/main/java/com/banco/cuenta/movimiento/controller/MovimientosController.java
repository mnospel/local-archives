package com.banco.cuenta.movimiento.controller;

import com.banco.cuenta.movimiento.model.Movimientos;
import com.banco.cuenta.movimiento.model.dto.ReporteMovimientos;
import com.banco.cuenta.movimiento.service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientosController {
    @Autowired
    private MovimientosService movimientosService;

    @PostMapping
    public ResponseEntity<Movimientos> registrarMovimiento(@RequestParam String numeroCuenta, @RequestBody Movimientos movimiento) {
        return new ResponseEntity<>(movimientosService.registrarMovimiento(numeroCuenta, movimiento), HttpStatus.OK);
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<List<Movimientos>> obtenerMovimientos(@PathVariable String numeroCuenta) {
        return new ResponseEntity<>(movimientosService.obtenerMovimientosPorCuenta(numeroCuenta), HttpStatus.OK);
    }

    @GetMapping("/reporte")
    public ResponseEntity<List<ReporteMovimientos>> generarReporte(
            @RequestParam("fechaInicio") LocalDateTime fechaInicio,
            @RequestParam("fechaFin") LocalDateTime fechaFin,
            @RequestParam("clienteId") Long clienteId) {

        return new ResponseEntity<>(movimientosService.generarReporte(fechaInicio, fechaFin, clienteId), HttpStatus.OK);
    }
}
