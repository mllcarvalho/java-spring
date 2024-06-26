package com.algaworks.algatransito.api.assembler;

import com.algaworks.algatransito.api.model.VeiculoModel;
import com.algaworks.algatransito.api.model.input.VeiculoInputModel;
import com.algaworks.algatransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class VeiculoAssembler {

    private final ModelMapper modelMapper;

    public Veiculo toEntity(VeiculoInputModel veiculoInputModel) {

        return modelMapper.map(veiculoInputModel, Veiculo.class);
    }

    public VeiculoModel toModel(Veiculo veiculo) {

        return modelMapper.map(veiculo, VeiculoModel.class);
    }

    public List<VeiculoModel> toCollectionModel(List<Veiculo> veiculos) {
        return veiculos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
