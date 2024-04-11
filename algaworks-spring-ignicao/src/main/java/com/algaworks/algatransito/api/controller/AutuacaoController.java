package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.assembler.AutuacaoAssembler;
import com.algaworks.algatransito.api.assembler.VeiculoAssembler;
import com.algaworks.algatransito.api.model.AutuacaoModel;
import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.api.model.input.AutuacaoInputModel;
import com.algaworks.algatransito.api.model.input.VeiculoInputModel;
import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.AutuacaoRepository;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.domain.service.RegistroAutuacaoService;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AutuacaoController {

    private final RegistroAutuacaoService registroAutuacaoService;
    private final AutuacaoRepository autuacaoRepository;
    private final AutuacaoAssembler autuacaoAssembler;

    @GetMapping("/autuacoes")
    public List<AutuacaoModel> listar() {

        return autuacaoAssembler.toCollectionModel(autuacaoRepository.findAll());
    }

    @GetMapping("/autuacoes/{autuacaoId}")
    public ResponseEntity<AutuacaoModel> Buscar(@PathVariable Long autuacaoId) {

        return autuacaoRepository.findById(autuacaoId)
                .map(autuacaoAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/veiculos/{veiculoId}/autuacoes")
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoModel adicionar(@PathVariable Long veiculoId,
                                   @Valid @RequestBody AutuacaoInputModel autuacaoInputModel) {

        Autuacao autuacao = autuacaoAssembler.toEntity(autuacaoInputModel);
        Autuacao novaAutuacao = registroAutuacaoService.salvar(veiculoId, autuacao);

        return autuacaoAssembler.toModel(novaAutuacao);
    }

//    @PutMapping("/{veiculoId}")
//    public ResponseEntity<Veiculo> alterar(@PathVariable Long veiculoId, @Valid @RequestBody Veiculo veiculo) {
//
//        if (!veiculoRepository.existsById(veiculoId)) {
//            return ResponseEntity.notFound().build();
//        }
//
//        veiculo.setId(veiculoId);
//        veiculo = registroVeiculoService.salvar(veiculo);
//
//        return ResponseEntity.ok(veiculo);
//    }

    @DeleteMapping("/autuacoes/{autuacaoId}")
    public ResponseEntity<Autuacao> remover(@PathVariable Long autuacaoId) {

        if (!autuacaoRepository.existsById(autuacaoId)) {
            return ResponseEntity.notFound().build();
        }

        registroAutuacaoService.excluir(autuacaoId);
        return ResponseEntity.noContent().build();
    }
}
