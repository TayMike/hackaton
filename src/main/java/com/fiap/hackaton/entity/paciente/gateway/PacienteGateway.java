package com.fiap.hackaton.entity.paciente.gateway;

import com.fiap.hackaton.entity.paciente.model.Paciente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PacienteGateway {
    Paciente save(Paciente paciente);
    Optional<Paciente> findById(UUID id);
    List<Paciente> findAll();
    Paciente update(Paciente paciente);
}