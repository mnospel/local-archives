package com.banco.cliente.service.impl;

import com.banco.cliente.exception.custom.ConflictException;
import com.banco.cliente.model.Cliente;
import com.banco.cliente.model.Persona;
import com.banco.cliente.repository.ClienteRepository;
import com.banco.cliente.repository.PersonaRepository;
import com.banco.cliente.service.ClienteService;
import com.banco.cliente.utils.ClienteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PersonaRepository personaRepository;
    private final ClienteMapper clienteMapper;
    private final String ERROR_EXCEPTION_MESSAGE = "Error ocurred: {}";

    @Override
    public Cliente crearCliente(Cliente cliente) {
        log.info("crearCliente started");
        try {
            Optional<Persona> personaExistente = Optional.ofNullable(personaRepository
                    .findByIdentificacion(cliente.getPersona().getIdentificacion()));
            if (personaExistente.isEmpty()) {
                Persona savedPersona = personaRepository.save(cliente.getPersona());
                cliente.setPersona(savedPersona);
                return clienteRepository.save(cliente);
            }
            else {
                throw new ConflictException("Ya existe el cliente registrado con la identificación.");
            }
        }
        catch (Exception ex) {
            log.debug(ERROR_EXCEPTION_MESSAGE, ex.getMessage());
            throw ex;
        }
        finally {
            log.info("crearCliente completed");
        }
    }

    @Override
    public List<Cliente> listarClientes() {
        log.info("listarClientes started");
        try {
            return clienteRepository.findAll();
        }
        catch (Exception ex) {
            log.debug(ERROR_EXCEPTION_MESSAGE, ex.getMessage());
            throw ex;
        }
        finally {
            log.info("listarClientes completed");
        }
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente cliente) {
        log.info("actualizarCliente started");
        try {
            Optional<Cliente> clienteExistente = clienteRepository.findById(id);
            if (clienteExistente.isPresent()) {
                Cliente clienteActualizado = clienteMapper.toCliente(clienteExistente.get(), cliente);
                personaRepository.save(clienteActualizado.getPersona());
                return clienteRepository.save(clienteActualizado);
            } else {
                log.debug("actualizarCliente called");
                throw new RuntimeException("Cliente no encontrado con ID: " + id);
            }
        }
        catch (Exception ex) {
            log.debug(ERROR_EXCEPTION_MESSAGE, ex.getMessage());
            throw ex;
        }
        finally {
            log.info("actualizarCliente completed");
        }
    }

    @Override
    public void eliminarCliente(Long id) {
        log.info("eliminarCliente started");
        try {
            if (clienteRepository.existsById(id)) {
                personaRepository.deleteById(id);
                clienteRepository.deleteById(id);
            } else {
                log.debug("eliminarCliente called");
                throw new RuntimeException("Cliente no encontrado con ID: " + id);
            }
        }
        catch (Exception ex) {
            log.debug(ERROR_EXCEPTION_MESSAGE, ex.getMessage());
            throw ex;
        }
        finally {
            log.info("eliminarCliente completed");
        }
    }

    @Override
    public Cliente obtenerCliente(Long id) {
        log.info("obtenerCliente started");
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (cliente.isPresent()) {
                return cliente.get();
            } else {
                log.debug("obtenerCliente called");
                throw new RuntimeException("Cliente no encontrado con ID: " + id);
            }
        }
        catch (Exception ex) {
            log.debug(ERROR_EXCEPTION_MESSAGE, ex.getMessage());
            throw ex;
        }
        finally {
            log.info("obtenerCliente completed");
        }
    }
}
