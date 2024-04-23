package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
public class EstadoRepositoryImpl implements EstadoRepository {

        EntityManager entityManager;

        @Override
        public List<Estado> listar() {
            return entityManager.createQuery("from Estado", Estado.class)
                    .getResultList();
        }

        @Override
        public Estado buscar(Long id) {
            return entityManager.find(Estado.class, id);
        }

        @Override
        @Transactional
        public Estado salvar(Estado estado) {
            return entityManager.merge(estado);
        }

        @Override
        @Transactional
        public void remover(Long estadoId) {
            Estado estado = buscar(estadoId);

            if (estado == null) {
                throw new EmptyResultDataAccessException(1);
            }

            entityManager.remove(estado);
        }

}
