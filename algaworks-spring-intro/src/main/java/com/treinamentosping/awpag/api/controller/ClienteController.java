package com.treinamentosping.awpag.api.controller;

import com.treinamentosping.awpag.domain.repository.ClienteRepository;
import com.treinamentosping.awpag.domain.model.Cliente;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<Cliente> listar() {

        return clienteRepository.findAll();
    }
}
