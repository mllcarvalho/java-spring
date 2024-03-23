package com.treinamentosping.awpag.api.controller;

import com.treinamentosping.awpag.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {
    @GetMapping("/clientes")
    public List<Cliente> listar() {
        var cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("João");
        cliente1.setEmail("joao@gmail.com");
        cliente1.setTelefone("11 1111-1111");

        var cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Maria");
        cliente2.setEmail("maria@gmail.com");
        cliente2.setTelefone("11 2222-2222");

        return Arrays.asList(cliente1,cliente2);
    }
}
