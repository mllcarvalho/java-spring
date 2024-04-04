package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
@AllArgsConstructor
public class VeiculoController {

    private final RegistroVeiculoService registroVeiculoService;
    private final VeiculoRepository veiculoRepository;

    @GetMapping
    public List<Veiculo> listar() {

        return veiculoRepository.findAll();
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoModel> Buscar(@PathVariable Long veiculoId) {

        return veiculoRepository.findById(veiculoId)
                .map(veiculo -> {
                    var veiculoModel = new VeiculoModel();
                    veiculoModel.setId(veiculo.getId());
                    veiculoModel.setPlaca(veiculo.getPlaca());
                    veiculoModel.setMarca(veiculo.getMarca());
                    veiculoModel.setModelo(veiculo.getModelo());
                    veiculoModel.setDataCadastro(veiculo.getDataCadastro());
                    veiculoModel.setDataApreensao(veiculo.getDataApreensao());
                    veiculoModel.setStatus(veiculo.getStatus());
                    veiculoModel.setNomeProprietario(veiculo.getProprietario().getNome());
                    return veiculoModel;
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo adicionar(@Valid @RequestBody Veiculo veiculo) {
            
        return registroVeiculoService.salvar(veiculo);
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
}
