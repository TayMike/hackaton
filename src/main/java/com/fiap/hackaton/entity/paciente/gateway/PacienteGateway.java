package com.fiap.hackaton.entity.paciente.gateway;

import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.infrastructure.config.db.schema.PacienteSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PacienteGateway {
    Paciente save(Paciente paciente);
    Optional<PacienteSchema> findById(UUID id);
    List<Paciente> findAll();
    Paciente update(Paciente paciente);
}