package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository;

import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.PacienteSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteSchema, UUID> {
}