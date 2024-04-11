package com.algaworks.algafood.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/teste")
public class Teste {

    @GetMapping
    public String teste() {
        return "Teste";
    }
}
