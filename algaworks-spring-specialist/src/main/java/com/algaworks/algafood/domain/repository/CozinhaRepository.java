package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
public class CozinhaRepository {

        EntityManager entityManager;

        public List<Cozinha> buscar() {
            return entityManager.createQuery("from Cozinha", Cozinha.class)
                    .getResultList();
        }

        public Cozinha buscarPorId(Long id) {
            return entityManager.find(Cozinha.class, id);
        }

        @Transactional
        public Cozinha salvar(Cozinha cozinha) {
            return entityManager.merge(cozinha);
        }

        @Transactional
        public void remover(Cozinha cozinha) {
            cozinha = buscarPorId(cozinha.getId());
            entityManager.remove(cozinha);
        }

}
