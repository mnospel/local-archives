package com.banco.cuenta.movimiento.client;

import com.banco.cuenta.movimiento.model.Cliente;
import java.util.Optional;

public interface ClientesClient {
    Optional<Cliente> obtenerCliente(Long clienteId);
}
