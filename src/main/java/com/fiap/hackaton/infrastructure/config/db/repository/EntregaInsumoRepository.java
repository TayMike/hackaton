package com.fiap.hackaton.infrastructure.config.db.repository;

import com.fiap.hackaton.infrastructure.config.db.schema.EntregaInsumoSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EntregaInsumoRepository extends JpaRepository<EntregaInsumoSchema, UUID> {
}