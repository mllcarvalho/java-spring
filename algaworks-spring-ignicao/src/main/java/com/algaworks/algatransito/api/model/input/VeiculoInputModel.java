package com.algaworks.algatransito.api.model.input;

import com.algaworks.algatransito.api.model.ProprietarioIdModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoInputModel {

    @NotNull
    @Valid
    private ProprietarioIdModel proprietario;

    @NotBlank
    @Size(max = 8)
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String placa;

    @NotBlank
    @Size(max = 60)
    private String modelo;

    @NotBlank
    @Size(max = 60)
    private String marca;
}
