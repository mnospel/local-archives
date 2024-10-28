package com.banco.cuenta.movimiento.controller;

import com.banco.cuenta.movimiento.model.Cuenta;
import com.banco.cuenta.movimiento.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        return new ResponseEntity<>(cuentaService.crearCuenta(cuenta), HttpStatus.CREATED);
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<Cuenta> obtenerCuenta(@PathVariable String numeroCuenta) {
        return new ResponseEntity<>(cuentaService.obtenerCuenta(numeroCuenta), HttpStatus.OK);
    }

    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable String numeroCuenta, @RequestBody Cuenta cuenta) {
        return new ResponseEntity<>(cuentaService.actualizarCuenta(numeroCuenta, cuenta), HttpStatus.OK);
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable String numeroCuenta) {
        cuentaService.eliminarCuenta(numeroCuenta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all/{clienteId}")
    public ResponseEntity<List<Cuenta>> listarCuentas(@PathVariable Long clienteId) {
        return new ResponseEntity<>(cuentaService.listarCuentas(clienteId), HttpStatus.OK);
    }
}
