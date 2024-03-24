package com.treinamentosping.awpag.api.controller;

import com.treinamentosping.awpag.domain.exception.NegocioException;
import com.treinamentosping.awpag.domain.model.Cliente;
import com.treinamentosping.awpag.domain.model.Parcelamento;
import com.treinamentosping.awpag.domain.repository.ParcelamentoRepository;
import com.treinamentosping.awpag.domain.service.ParcelamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {

    private final ParcelamentoService parcelamentoService;
    private final ParcelamentoRepository parcelamentoRepository;

    @GetMapping
    public List<Parcelamento> listar() {
        return parcelamentoRepository.findAll();
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<Parcelamento> buscar(@PathVariable Long parcelamentoId) {

        return parcelamentoRepository.findById(parcelamentoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Parcelamento adicionar(@RequestBody Parcelamento parcelamento){

        return parcelamentoService.cadastrar(parcelamento);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e) {

        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
