package com.algaworks.algatransito.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Long id;

    @ManyToOne
    @JoinColumn(name = "proprietario_id")
    public Proprietario proprietario;

    public String placa;

    public String marca;

    public String modelo;

    @Enumerated(EnumType.STRING)
    public StatusVeiculo status;

    @CreatedDate
    public OffsetDateTime dataCadastro;

    public OffsetDateTime dataApreensao;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    public List<Autuacao> autuacoes = new ArrayList<>();

    public Autuacao adicionarAutuacao(Autuacao autuacao) {
        this.autuacoes.add(autuacao);
        autuacao.setVeiculo(this);
        autuacao.setDataOcorrencia(OffsetDateTime.now());

        return autuacao;
    }
}
