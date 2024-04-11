package com.algaworks.algatransito.api.model;

import com.algaworks.algatransito.domain.model.StatusVeiculo;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class VeiculoAutuacaoModel {
    private Long id;
    private ProprietarioResumoModel proprietario;
    private String numeroPlaca;
    private String modelo;
    private String marca;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;
    private StatusVeiculo status;
    private List<AutuacaoModel> autuacoes;
}
