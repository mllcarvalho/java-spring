package com.algaworks.algatransito.domain.model;

import com.algaworks.algatransito.domain.validation.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ProprietarioId.class)
    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    @NotNull
    public Proprietario proprietario;

    @NotBlank
    @Size(max = 7)
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    public String placa;

    @NotBlank
    @Size(max = 20)
    public String marca;

    @NotBlank
    @Size(max = 20)
    public String modelo;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public StatusVeiculo status;

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public LocalDateTime dataCadastro;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public LocalDateTime dataApreensao;

}
