package com.banco.cliente.service;

import com.banco.cliente.model.Cliente;
import java.util.List;

public interface ClienteService {
    Cliente crearCliente(Cliente cliente);
    List<Cliente> listarClientes();
    Cliente actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(Long id);
    Cliente obtenerCliente(Long id);
}
