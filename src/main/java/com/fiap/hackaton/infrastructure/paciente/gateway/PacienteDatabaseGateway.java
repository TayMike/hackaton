package com.fiap.hackaton.infrastructure.paciente.gateway;

import com.fiap.hackaton.entity.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.infrastructure.config.db.repository.PacienteRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.PacienteSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class PacienteDatabaseGateway implements PacienteGateway {

    private final PacienteRepository pacienteRepository;

    public PacienteDatabaseGateway(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(new PacienteSchema(paciente)).toPaciente();
    }

    @Override
    public Optional<PacienteSchema> findById(UUID id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll()
                .stream()
                .map(PacienteSchema::toPaciente)
                .collect(Collectors.toList());
    }

    @Override
    public Paciente update(Paciente paciente) {
        PacienteSchema updated = pacienteRepository.save(new PacienteSchema(paciente));
        return updated.toPaciente();
    }
}