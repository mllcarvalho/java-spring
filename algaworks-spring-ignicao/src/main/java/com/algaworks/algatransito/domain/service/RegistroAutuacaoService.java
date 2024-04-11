package com.algaworks.algatransito.domain.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.AutuacaoRepository;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class RegistroAutuacaoService {

    private final AutuacaoRepository autuacaoRepository;
    private final RegistroVeiculoService registroVeiculoService;

    @Transactional
    public Autuacao salvar(Long veiculoId, Autuacao autuacao) {

        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);

        return veiculo.adicionarAutuacao(autuacao);
    }

    @Transactional
    public void excluir(Long autuacaoId) {
        autuacaoRepository.deleteById(autuacaoId);
    }
}
