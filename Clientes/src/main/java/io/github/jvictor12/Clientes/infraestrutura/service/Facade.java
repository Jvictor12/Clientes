package io.github.jvictor12.Clientes.infraestrutura.service;

import io.github.jvictor12.Clientes.cliente.Cliente;
import io.github.jvictor12.Clientes.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Facade {

    @Autowired
    public ClienteService clienteService;

    public List<Cliente> clienteFindAll(){ return clienteService.findAll(); };

    public Cliente clienteFindById(String id) { return clienteService.findById(id); }

    public Cliente clienteSave (Cliente cliente) { return clienteService.save(cliente); }

}
