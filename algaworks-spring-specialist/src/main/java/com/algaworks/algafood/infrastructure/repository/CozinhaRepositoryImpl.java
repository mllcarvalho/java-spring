package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
public class CozinhaRepositoryImpl implements CozinhaRepository {

        EntityManager entityManager;

        @Override
        public List<Cozinha> listar() {
            return entityManager.createQuery("from Cozinha", Cozinha.class)
                    .getResultList();
        }

        @Override
        public Cozinha buscar(Long id) {
            return entityManager.find(Cozinha.class, id);
        }

        @Override
        @Transactional
        public Cozinha salvar(Cozinha cozinha) {
            return entityManager.merge(cozinha);
        }

        @Override
        @Transactional
        public void remover(Long cozinhaId) {
            Cozinha cozinha = buscar(cozinhaId);

            if (cozinha == null) {
                throw new EmptyResultDataAccessException(1);
            }

            entityManager.remove(cozinha);
        }
}
