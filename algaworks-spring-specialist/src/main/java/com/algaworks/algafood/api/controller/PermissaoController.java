package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import com.algaworks.algafood.domain.service.PermissaoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/permissoes")
public class PermissaoController {

    private final PermissaoRepository permissaoRepository;
    private final PermissaoService permissaoService;

    @GetMapping
    public List<Permissao> listar() {
        return permissaoRepository.findAll();
    }

    @GetMapping("/{permissaoId}")
    public ResponseEntity<Permissao> buscar(@PathVariable Long permissaoId) {
        Optional<Permissao> permissao = permissaoRepository.findById(permissaoId);
        return permissao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Permissao adicionar(@RequestBody Permissao permissao) {
        return permissaoService.salvar(permissao);
    }

    @PutMapping("/{permissaoId}")
    public ResponseEntity<Permissao> atualizar(@PathVariable Long permissaoId, @RequestBody Permissao permissao) {
        Optional<Permissao> permissaoAtual = permissaoRepository.findById(permissaoId);

        if (permissaoAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(permissao, permissaoAtual.get(), "id");
        Permissao permissaoSalva = permissaoService.salvar(permissaoAtual.get());

        return ResponseEntity.ok(permissaoSalva);
    }

    @DeleteMapping("/{permissaoId}")
    public ResponseEntity<Permissao> remover(@PathVariable Long permissaoId) {
        Optional<Permissao> permissaoAtual = permissaoRepository.findById(permissaoId);

        if (permissaoAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        permissaoService.excluir(permissaoId);
        return ResponseEntity.noContent().build();
    }
}
