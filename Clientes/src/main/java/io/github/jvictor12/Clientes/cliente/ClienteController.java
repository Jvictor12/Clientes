package io.github.jvictor12.Clientes.cliente;

import io.github.jvictor12.Clientes.infraestrutura.service.Facade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RestController
@RequestMapping ("/clientes")
public class ClienteController {

    @Autowired
    public Facade facade;

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(facade.clienteFindAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id){
            return ResponseEntity.status(HttpStatus.OK).body(facade.clienteFindById(id));
    }

    @PostMapping
    public ResponseEntity safe (@RequestBody @Valid Cliente cliente){
            clienteRepository.save(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity update (@RequestBody @Valid Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new Exception("Cliente nulo");
        } else {
            Optional<Cliente> cliente1 = clienteRepository.findById(cliente.getId());
            cliente.setDataCadastro(cliente1.get().getDataCadastro());
            clienteRepository.save(cliente);
        }

        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable String id){

        clienteRepository.deleteById(id);

        clienteRepository.findById(id)
                .map( cliente -> {
                    clienteRepository.delete(cliente);
                    return ResponseEntity.status(HttpStatus.OK).body(null);
                })
                .orElseThrow( () ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        return null;
    }
}
