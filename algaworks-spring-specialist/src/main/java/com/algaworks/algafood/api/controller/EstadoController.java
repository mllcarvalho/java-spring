package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/estados")
public class EstadoController {

    private final EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId) {
        return estadoRepository.buscar(estadoId);
    }

    @PostMapping
    public Estado adicionar(@RequestBody Estado estado) {
        return estadoRepository.salvar(estado);
    }

    @PutMapping("/{estadoId}")
    public Estado atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
        Estado estadoAtual = estadoRepository.buscar(estadoId);
        estadoAtual.setNome(estado.getNome());
        return estadoRepository.salvar(estadoAtual);
    }

    @DeleteMapping("/{estadoId}")
    public void remover(@PathVariable Long estadoId) {
        Estado estado = estadoRepository.buscar(estadoId);
        estadoRepository.remover(estado);
    }
}
