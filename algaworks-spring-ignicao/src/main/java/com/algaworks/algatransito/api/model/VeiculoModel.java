package com.algaworks.algatransito.api.model;

import com.algaworks.algatransito.domain.model.StatusVeiculo;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class VeiculoModel {

    private Long id;
    private String nomeProprietario;
    private String numeroPlaca;
    private String modelo;
    private String marca;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;
    private StatusVeiculo status;
}