package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository;

import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.EstoqueEquipamentoSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstoqueEquipamentoRepository extends JpaRepository<EstoqueEquipamentoSchema, UUID> {
}
