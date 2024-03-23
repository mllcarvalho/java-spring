package com.treinamentosping.awpag.api.controller;

import com.treinamentosping.awpag.domain.repository.ClienteRepository;
import com.treinamentosping.awpag.domain.model.Cliente;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {

        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId)
    {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if (cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody Cliente cliente){

        return clienteRepository.save(cliente);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clientId, @RequestBody Cliente cliente){

        if (!clienteRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clientId);
        cliente = clienteRepository.save(cliente);

        return ResponseEntity.ok(cliente);

    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> excluir(@PathVariable Long clientId) {

        if (!clienteRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }

        clienteRepository.deleteById(clientId);

        return ResponseEntity.noContent().build();
    }
}
