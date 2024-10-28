package com.banco.cuenta.movimiento.service;

import com.banco.cuenta.movimiento.model.Cuenta;
import java.util.List;

public interface CuentaService {
    Cuenta crearCuenta(Cuenta cuenta);
    Cuenta obtenerCuenta(String numeroCuenta);
    Cuenta actualizarCuenta(String numeroCuenta, Cuenta cuenta);
    void eliminarCuenta(String id);
    List<Cuenta> listarCuentas(Long clienteId);
}