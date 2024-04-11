package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.assembler.VeiculoAssembler;
import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.api.model.input.VeiculoInputModel;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.domain.service.ApreensaoVeiculoService;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
@AllArgsConstructor
public class VeiculoController {

    private final RegistroVeiculoService registroVeiculoService;
    private final ApreensaoVeiculoService apreensaoVeiculoService;
    private final VeiculoRepository veiculoRepository;
    private final VeiculoAssembler veiculoAssembler;

    @GetMapping
    public List<VeiculoModel> listar() {

        return veiculoAssembler.toCollectionModel(veiculoRepository.findAll());
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoModel> Buscar(@PathVariable Long veiculoId) {

        return veiculoRepository.findById(veiculoId)
                .map(veiculoAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoModel adicionar(@Valid @RequestBody VeiculoInputModel veiculoInputModel) {

        Veiculo veiculo = veiculoAssembler.toEntity(veiculoInputModel);
        Veiculo novoVeiculo = registroVeiculoService.salvar(veiculo);

        return veiculoAssembler.toModel(novoVeiculo);
    }

    @PutMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> alterar(@PathVariable Long veiculoId, @Valid @RequestBody Veiculo veiculo) {

        if (!veiculoRepository.existsById(veiculoId)) {
            return ResponseEntity.notFound().build();
        }

        veiculo.setId(veiculoId);
        veiculo = registroVeiculoService.salvar(veiculo);

        return ResponseEntity.ok(veiculo);
    }

    @DeleteMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> remover(@PathVariable Long veiculoId) {

        if (!veiculoRepository.existsById(veiculoId)) {
            return ResponseEntity.notFound().build();
        }

        registroVeiculoService.excluir(veiculoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apreender(@PathVariable Long veiculoId) {

        apreensaoVeiculoService.apreender(veiculoId);
    }

    @DeleteMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void liberar(@PathVariable Long veiculoId) {

        apreensaoVeiculoService.liberar(veiculoId);
    }
}
