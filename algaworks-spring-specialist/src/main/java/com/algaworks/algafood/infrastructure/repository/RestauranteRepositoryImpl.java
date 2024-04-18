package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
public class RestauranteRepositoryImpl implements RestauranteRepository {

        EntityManager entityManager;

        @Override
        public List<Restaurante> listar() {
            return entityManager.createQuery("from Restaurante", Restaurante.class)
                    .getResultList();
        }

        @Override
        public Restaurante buscar(Long id) {
            return entityManager.find(Restaurante.class, id);
        }

        @Override
        @Transactional
        public Restaurante salvar(Restaurante restaurante) {
            return entityManager.merge(restaurante);
        }

        @Override
        @Transactional
        public void remover(Restaurante restaurante) {
            restaurante = buscar(restaurante.getId());
            entityManager.remove(restaurante);
        }
}
