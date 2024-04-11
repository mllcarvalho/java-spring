package com.algaworks.algatransito.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AutuacaoInputModel {

    @NotBlank
    private String descricao;

    @NotBlank
    private BigDecimal valorMulta;
}
