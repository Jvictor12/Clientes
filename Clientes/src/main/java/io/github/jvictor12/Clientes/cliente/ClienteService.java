package io.github.jvictor12.Clientes.cliente;

import io.github.jvictor12.Clientes.infraestrutura.exception.ObjectNotFoundException;
import io.github.jvictor12.Clientes.infraestrutura.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(String id) {
        return clienteRepository.findById(id).orElseThrow( ()-> {
            throw new ObjectNotFoundException("Cliente não encontrado");
        });
    }

    public Cliente save(Cliente cliente) {

        if (cliente == null) {
            throw new ValidationException("Sem dados");
        }

        if(validateCliente(cliente)){
            return clienteRepository.save(cliente);
        }
        return cliente;
    }

    public Cliente update(Cliente cliente) {

        if (cliente == null) {
            throw new ValidationException("Sem dados");
        }

        if (!clienteRepository.existsById(cliente.getId())){
            throw new ValidationException("O cliente não existe no sistema");
        }

        if(validateCliente(cliente)){
            return clienteRepository.save(cliente);
        }

        return cliente;
    }

    public void delete (Cliente cliente) {

        if(!clienteRepository.existsById(cliente.getId())){
            throw new ObjectNotFoundException("Cliente não encontrado");
        }

        clienteRepository.deleteById(cliente.getId());
    }

    public boolean validateCliente(Cliente cliente) {
        Cliente clienteFindByCPF = clienteRepository.findByCPF(cliente.getCPF());

        if(clienteFindByCPF != null && !clienteFindByCPF.equals(cliente)){
            throw new ValidationException("CPF do cliente ja cadastrado no sistema");
        }

        return true;
    }
}
