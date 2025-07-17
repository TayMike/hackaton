package com.fiap.hackaton.infrastructure.config.db.repository;

import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EquipamentoRepository extends JpaRepository<EquipamentoSchema, UUID> {
}
