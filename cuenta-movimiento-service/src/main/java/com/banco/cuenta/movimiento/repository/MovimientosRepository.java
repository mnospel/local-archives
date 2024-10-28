package com.banco.cuenta.movimiento.repository;

import com.banco.cuenta.movimiento.model.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientosRepository extends JpaRepository<Movimientos, Long> {
    List<Movimientos> findByCuentaCuentaId(String numeroCuenta);
    List<Movimientos> findByFechaBetweenAndCuentaCuentaIdIn(LocalDateTime fechaInicio, LocalDateTime fechaFin, List<String> cuentaIds);
    List<Movimientos> findByCuentaCuentaIdIn(List<String> cuentaIds);
}
