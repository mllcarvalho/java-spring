package com.treinamentosping.awpag.domain.service;

import com.treinamentosping.awpag.domain.exception.NegocioException;
import com.treinamentosping.awpag.domain.model.Cliente;
import com.treinamentosping.awpag.domain.model.Parcelamento;
import com.treinamentosping.awpag.domain.repository.ClienteRepository;
import com.treinamentosping.awpag.domain.repository.ParcelamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ParcelamentoService {

    private final ParcelamentoRepository parcelamentoRepository;
    private final CadastroClienteService cadastroClienteService;

    @Transactional
    public Parcelamento cadastrar(Parcelamento parcelamento) {

        if (parcelamento.getId() != null) {
            throw new NegocioException("Parcelamento a ser criado não deve possuir um código");
        }

        Cliente cliente = cadastroClienteService.buscar(parcelamento.getCliente().getId());

        parcelamento.setCliente(cliente);
        parcelamento.setDataCriacao(LocalDateTime.now());

        return parcelamentoRepository.save(parcelamento);
    }
}
