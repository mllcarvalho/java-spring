package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.model.Proprietario;
import jakarta.persistence.EntityManager;
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
        List<Proprietario> proprietarioList = new ArrayList<>();
        return ResponseEntity.ok(proprietarioList);
    }
}
