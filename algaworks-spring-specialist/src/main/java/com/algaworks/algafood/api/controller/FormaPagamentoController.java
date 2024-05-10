package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.FormaPagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/formasPagamento")
public class FormaPagamentoController {

    private final FormaPagamentoRepository formaPagamentoRepository;
    private final FormaPagamentoService formaPagamentoService;

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
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamento adicionar(@RequestBody FormaPagamento formaPagamento) {
        return formaPagamentoService.salvar(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamento> atualizar(@PathVariable Long formaPagamentoId, @RequestBody FormaPagamento formaPagamento) {
        Optional<FormaPagamento> formaPagamentoAtual = formaPagamentoRepository.findById(formaPagamentoId);

        if (formaPagamentoAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual.get(), "id");
        FormaPagamento formaPagamentoSalva = formaPagamentoService.salvar(formaPagamentoAtual.get());

        return ResponseEntity.ok(formaPagamentoSalva);
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<?> remover(@PathVariable Long formaPagamentoId) {

        try {
            Optional<FormaPagamento> formaPagamentoAtual = formaPagamentoRepository.findById(formaPagamentoId);

            if (formaPagamentoAtual.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            formaPagamentoService.excluir(formaPagamentoId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
