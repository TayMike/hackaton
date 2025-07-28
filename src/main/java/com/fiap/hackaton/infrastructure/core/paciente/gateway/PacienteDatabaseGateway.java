package com.fiap.hackaton.infrastructure.core.paciente.gateway;

import com.fiap.hackaton.entity.core.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.PacienteRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.PacienteSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PacienteDatabaseGateway implements PacienteGateway {

    private final PacienteRepository pacienteRepository;

    public PacienteDatabaseGateway(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(new PacienteSchema(paciente)).toEntity();
    }

    @Override
    public Optional<Paciente> findById(UUID id) {
        return pacienteRepository.findById(id).map(PacienteSchema::toEntity);
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll().stream().map(PacienteSchema::toEntity).toList();
    }

    @Override
    public Paciente update(Paciente paciente) {
        return pacienteRepository.save(new PacienteSchema(paciente)).toEntity();
    }
}