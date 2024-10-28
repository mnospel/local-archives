package com.banco.cliente.utils;

import com.banco.cliente.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(target = "clienteId", source = "clienteExistente.clienteId")
    @Mapping(target = "contrasena", source = "clienteActualizado.contrasena")
    @Mapping(target = "estado", source = "clienteActualizado.estado")
    @Mapping(target = "persona.personaId", source = "clienteExistente.persona.personaId")
    @Mapping(target = "persona.nombre", source = "clienteActualizado.persona.nombre")
    @Mapping(target = "persona.genero", source = "clienteActualizado.persona.genero")
    @Mapping(target = "persona.edad", source = "clienteActualizado.persona.edad")
    @Mapping(target = "persona.identificacion", source = "clienteExistente.persona.identificacion")
    @Mapping(target = "persona.direccion", source = "clienteActualizado.persona.direccion")
    @Mapping(target = "persona.telefono", source = "clienteActualizado.persona.telefono")
    Cliente toCliente(Cliente clienteExistente, Cliente clienteActualizado);
}
