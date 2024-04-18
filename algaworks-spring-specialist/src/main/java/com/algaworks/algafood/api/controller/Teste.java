package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cozinha")
public class Teste {

    private final CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.buscar();
    }

    @GetMapping("/{cozinhaId}")
    public Cozinha buscar(@PathVariable Long cozinhaId) {
        return cozinhaRepository.buscarPorId(cozinhaId);
    }

    @PostMapping
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public Cozinha atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaRepository.buscarPorId(cozinhaId);
        cozinhaAtual.setNome(cozinha.getNome());
        return cozinhaRepository.salvar(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    public void remover(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cozinhaRepository.buscarPorId(cozinhaId);
        cozinhaRepository.remover(cozinha);
    }
}
