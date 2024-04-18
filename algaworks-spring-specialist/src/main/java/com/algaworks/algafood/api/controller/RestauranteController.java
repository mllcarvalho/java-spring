package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurante")
public class RestauranteController {

    private final RestauranteRepository restauranteRepository;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    @GetMapping("/{restauranteId}")
    public Restaurante buscar(@PathVariable Long restauranteId) {
        return restauranteRepository.buscar(restauranteId);
    }

    @PostMapping
    public Restaurante adicionar(@RequestBody Restaurante restaurante) {
        return restauranteRepository.salvar(restaurante);
    }

    @PutMapping("/{restauranteId}")
    public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
        Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);
        restauranteAtual.setNome(restaurante.getNome());
        restauranteAtual.setTaxaFrete(restaurante.getTaxaFrete());
        return restauranteRepository.salvar(restauranteAtual);
    }

    @DeleteMapping("/{restauranteId}")
    public void remover(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteRepository.buscar(restauranteId);
        restauranteRepository.remover(restaurante);
    }
}
