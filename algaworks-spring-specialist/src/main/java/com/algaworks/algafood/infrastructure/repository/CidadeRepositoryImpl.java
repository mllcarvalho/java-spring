package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
public class CidadeRepositoryImpl implements CidadeRepository {

        EntityManager entityManager;

        @Override
        public List<Cidade> listar() {
            return entityManager.createQuery("from Cidade", Cidade.class)
                    .getResultList();
        }

        @Override
        public Cidade buscar(Long id) {
            return entityManager.find(Cidade.class, id);
        }

        @Override
        @Transactional
        public Cidade salvar(Cidade cidade) {
            return entityManager.merge(cidade);
        }

        @Override
        @Transactional
        public void remover(Long cidadeId) {
            Cidade cidade = buscar(cidadeId);

            if (cidade == null) {
                throw new EmptyResultDataAccessException(1);
            }

            entityManager.remove(cidade);
        }
}
