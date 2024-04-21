package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/formasPagamento")
public class FormaPagamentoController {

    private final FormaPagamentoRepository formaPagamentoRepository;

    @GetMapping
    public List<FormaPagamento> listar() {
        return formaPagamentoRepository.listar();
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamento buscar(@PathVariable Long formaPagamentoId) {
        return formaPagamentoRepository.buscar(formaPagamentoId);
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
