package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeDuplicadaException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CadastroRestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CozinhaRepository cozinhaRepository;
    private final FormaPagamentoRepository formaPagamentoRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        List<FormaPagamento> formasPagamento = restaurante.getFormasPagamento();
        List<FormaPagamento> formasPagamentoList = new ArrayList<>();

        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));

        formasPagamento.forEach(formaPagamento -> {
            Long formaPagamentoId = formaPagamento.getId();
            Optional<FormaPagamento> formaPagamentoOptional = formaPagamentoRepository.findById(formaPagamento.getId());

            if (formaPagamentoOptional.isEmpty()) {
                throw new EntidadeNaoEncontradaException(
                        String.format("Não existe cadastro de forma de pagamento com código %d", formaPagamento.getId()));
            }

            formasPagamentoList.stream()
                    .filter(formaPagamento1 -> formaPagamento1.getId().equals(formaPagamentoId))
                    .findAny()
                    .ifPresentOrElse(
                            formaPagamento1 -> {
                                throw new EntidadeDuplicadaException(
                                        String.format("Forma de pagamento com código %d duplicado", formaPagamento1.getId()));
                            },
                            () -> formasPagamentoList.add(formaPagamentoOptional.get()));
        });

        restaurante.setCozinha(cozinha);
        restaurante.setFormasPagamento(formasPagamentoList);

        return restauranteRepository.save(restaurante);
    }

    public void remover(Long restauranteId) {
        restauranteRepository.deleteById(restauranteId);
    }
}
