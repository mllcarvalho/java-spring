package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/formasPagamento")
public class FormaPagamentoController {

    private final FormaPagamentoRepository formaPagamentoRepository;

    @GetMapping
    public List<FormaPagamento> listar() {
        return formaPagamentoRepository.findAll();
    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamento> buscar(@PathVariable Long formaPagamentoId) {
        Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(formaPagamentoId);

        return formaPagamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public FormaPagamento adicionar(@RequestBody FormaPagamento formaPagamento) {
        return formaPagamentoRepository.salvar(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamento atualizar(@PathVariable Long formaPagamentoId, @RequestBody FormaPagamento formaPagamento) {
        FormaPagamento formaPagamentoAtual = formaPagamentoRepository.buscar(formaPagamentoId);
        formaPagamentoAtual.setDescricao(formaPagamento.getDescricao());
        return formaPagamentoRepository.salvar(formaPagamentoAtual);
    }

    @DeleteMapping("/{formaPagamentoId}")
    public void remover(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = formaPagamentoRepository.buscar(formaPagamentoId);
        formaPagamentoRepository.remover(formaPagamento);
    }
}
