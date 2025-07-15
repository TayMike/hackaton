package com.fiap.hackaton.infrastructure.config.db.repository;

import com.fiap.hackaton.entity.paciente.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
}