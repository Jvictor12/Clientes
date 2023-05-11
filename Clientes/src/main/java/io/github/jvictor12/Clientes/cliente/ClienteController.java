package io.github.jvictor12.Clientes.cliente;

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
    public ClienteRepository clienteRepository;


    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id){

        if (clienteRepository.existsById(id))
            return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findByUuID(id));
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
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
