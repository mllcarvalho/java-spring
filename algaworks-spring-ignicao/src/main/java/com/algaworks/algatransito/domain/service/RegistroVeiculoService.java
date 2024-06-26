package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final RegistroProprietarioService registroProprietarioService;

    @Transactional
    public Veiculo salvar(Veiculo veiculo) {

        if(veiculo.getId() != null){
            throw new NegocioException("Veículo não deve possuir um código");
        }

        boolean placaEmUso = veiculoRepository.findByPlaca(veiculo.getPlaca())
            .filter(veiculoExistente -> !veiculoExistente.equals(veiculo))
            .isPresent();

        if (placaEmUso) {
            throw new NegocioException("Placa já está em uso");
        }

        veiculo.setProprietario(registroProprietarioService.buscar(veiculo.getProprietario().getId()));
        veiculo.setDataCadastro(OffsetDateTime.now());
        veiculo.setStatus(StatusVeiculo.REGULAR);

        return veiculoRepository.save(veiculo);
    }

    @Transactional
    public void excluir(Long veiculoId) {
        veiculoRepository.deleteById(veiculoId);
    }

    public Veiculo buscar(Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Veículo não encontrado"));
    }
}
