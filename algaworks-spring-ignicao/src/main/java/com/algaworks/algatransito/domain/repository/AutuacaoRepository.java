package com.algaworks.algatransito.domain.repository;
import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutuacaoRepository extends JpaRepository<Autuacao, Long> {

}
