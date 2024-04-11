package com.algaworks.algatransito.api.assembler;

import com.algaworks.algatransito.api.model.AutuacaoModel;
import com.algaworks.algatransito.api.model.VeiculoAutuacaoModel;
import com.algaworks.algatransito.api.model.input.AutuacaoInputModel;
import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class VeiculoAutuacaoAssembler {

    private final ModelMapper modelMapper;

    public VeiculoAutuacaoModel toModel(Veiculo veiculo) {

        return modelMapper.map(veiculo, VeiculoAutuacaoModel.class);
    }
}
