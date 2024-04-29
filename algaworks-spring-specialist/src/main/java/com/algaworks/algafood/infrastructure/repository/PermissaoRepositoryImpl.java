package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@AllArgsConstructor
public class PermissaoRepositoryImpl implements PermissaoRepository {

        EntityManager entityManager;

        @Override
        public List<Permissao> listar() {
            return entityManager.createQuery("from Permissao", Permissao.class)
                    .getResultList();
        }

        @Override
        public Permissao buscar(Long id) {
            return entityManager.find(Permissao.class, id);
        }

        @Override
        @Transactional
        public Permissao salvar(Permissao permissao) {
            return entityManager.merge(permissao);
        }

        @Override
        @Transactional
        public void remover(Permissao permissao) {
            permissao = buscar(permissao.getId());
            entityManager.remove(permissao);
        }

}
