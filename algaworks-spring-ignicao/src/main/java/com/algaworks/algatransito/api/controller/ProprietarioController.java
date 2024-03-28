package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class ProprietarioController {

    @GetMapping("/proprietarios")
    public ResponseEntity<List<Proprietario>> listar() {

        var proprietarioList = new ArrayList<Proprietario>();

        var proprietario1 = new Proprietario();
        proprietario1.setId(1L);
        proprietario1.setNome("Matheus");
        proprietario1.setEmail("mlc@gmail.com");
        proprietario1.setTelefone("11988887777");

        var proprietario2 = new Proprietario();
        proprietario2.setId(2L);
        proprietario2.setNome("Adriana");
        proprietario2.setEmail("agmf@gmail.com");
        proprietario2.setTelefone("11944445555");

        proprietarioList.add(proprietario1);
        proprietarioList.add(proprietario2);

        return ResponseEntity.ok(proprietarioList);
    }
}
