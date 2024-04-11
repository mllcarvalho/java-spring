package com.algaworks.algatransito.api.assembler;

import com.algaworks.algatransito.api.model.AutuacaoModel;
import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.api.model.input.AutuacaoInputModel;
import com.algaworks.algatransito.api.model.input.VeiculoInputModel;
import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class AutuacaoAssembler {

    private final ModelMapper modelMapper;

    public Autuacao toEntity(AutuacaoInputModel autuacaoInputModel) {

        return modelMapper.map(autuacaoInputModel, Autuacao.class);
    }

    public AutuacaoModel toModel(Autuacao autuacao) {

        return modelMapper.map(autuacao, AutuacaoModel.class);
    }

    public List<AutuacaoModel> toCollectionModel(List<Autuacao> autuacoes) {
        return autuacoes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
