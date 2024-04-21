package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/permissoes")
public class PermissaoController {

    private final PermissaoRepository permissaoRepository;

    @GetMapping
    public List<Permissao> listar() {
        return permissaoRepository.listar();
    }

    @GetMapping("/{permissaoId}")
    public Permissao buscar(@PathVariable Long permissaoId) {
        return permissaoRepository.buscar(permissaoId);
    }

    @PostMapping
    public Permissao adicionar(@RequestBody Permissao permissao) {
        return permissaoRepository.salvar(permissao);
    }

    @PutMapping("/{permissaoId}")
    public Permissao atualizar(@PathVariable Long permissaoId, @RequestBody Permissao permissao) {
        Permissao permissaoAtual = permissaoRepository.buscar(permissaoId);
        permissaoAtual.setNome(permissao.getNome());
        permissaoAtual.setDescricao(permissao.getDescricao());
        return permissaoRepository.salvar(permissaoAtual);
    }

    @DeleteMapping("/{permissaoId}")
    public void remover(@PathVariable Long permissaoId) {
        Permissao permissao = permissaoRepository.buscar(permissaoId);
        permissaoRepository.remover(permissao);
    }
}
