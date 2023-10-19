package io.github.jvictor12.Clientes.infraestrutura.service;

import io.github.jvictor12.Clientes.cliente.Cliente;
import io.github.jvictor12.Clientes.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Facade {

    @Autowired
    public ClienteService clienteService;

    public List<Cliente> ClienteFindAll(){ return clienteService.findAll(); };

}
