package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository;

import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.EstoqueInsumoSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EstoqueInsumoRepository extends JpaRepository<EstoqueInsumoSchema, UUID> {

    @NativeQuery(value = "SELECT * FROM estoque_insumo WHERE hospital_id = :hospitalId")
    Optional<EstoqueInsumoSchema> findByHospitalId(@Param("hospitalId") UUID hospitalId);

}