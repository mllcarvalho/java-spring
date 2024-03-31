package com.algaworks.algatransito.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Size(max = 7)
    public String placa;

    @NotBlank
    @Size(max = 20)
    public String marca;

    @NotBlank
    @Size(max = 20)
    public String modelo;

    @NotBlank
    @Size(max = 20)
    public String status;

    @CreatedDate
    public LocalDateTime dataCadastro;

    public LocalDateTime dataApreensao;

}
