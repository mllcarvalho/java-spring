package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeRepository cidadeRepository;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    @GetMapping("/{cidadeId}")
    public Cidade buscar(@PathVariable Long cidadeId) {
        return cidadeRepository.buscar(cidadeId);
    }

    @PostMapping
    public Cidade adicionar(@RequestBody Cidade cidade) {
        return cidadeRepository.salvar(cidade);
    }

    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
        Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);
        cidadeAtual.setNome(cidade.getNome());
        return cidadeRepository.salvar(cidadeAtual);
    }

    @DeleteMapping("/{cidadeId}")
    public void remover(@PathVariable Long cidadeId) {
        Cidade cidade = cidadeRepository.buscar(cidadeId);
        cidadeRepository.remover(cidade);
    }
}
