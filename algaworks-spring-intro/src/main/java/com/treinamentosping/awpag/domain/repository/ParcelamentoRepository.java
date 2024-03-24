package com.treinamentosping.awpag.domain.repository;

import com.treinamentosping.awpag.domain.model.Parcelamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelamentoRepository extends JpaRepository<Parcelamento, Long> {

}
