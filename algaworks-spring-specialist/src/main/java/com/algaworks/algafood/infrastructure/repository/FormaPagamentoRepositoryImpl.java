package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@AllArgsConstructor
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

        EntityManager entityManager;

        @Override
        public List<FormaPagamento> listar() {
            return entityManager.createQuery("from FormaPagamento", FormaPagamento.class)
                    .getResultList();
        }

        @Override
        public FormaPagamento buscar(Long id) {
            return entityManager.find(FormaPagamento.class, id);
        }

        @Override
        @Transactional
        public FormaPagamento salvar(FormaPagamento formaPagamento) {
            return entityManager.merge(formaPagamento);
        }

        @Override
        @Transactional
        public void remover(FormaPagamento formaPagamento) {
            formaPagamento = buscar(formaPagamento.getId());
            entityManager.remove(formaPagamento);
        }

}
