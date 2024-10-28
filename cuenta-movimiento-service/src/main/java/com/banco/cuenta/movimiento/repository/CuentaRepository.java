package com.banco.cuenta.movimiento.repository;

import com.banco.cuenta.movimiento.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String> {
    List<Cuenta> findAllByClienteId(Long clienteId);
}
