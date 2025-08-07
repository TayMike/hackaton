package com.fiap.hackaton.infrastructure.core.paciente.gateway;

import com.fiap.hackaton.entity.core.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.PacienteRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.PacienteSchema;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PacienteDatabaseGateway implements PacienteGateway {

    private final PacienteRepository pacienteRepository;

    public PacienteDatabaseGateway(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    @Caching(
        evict = {@CacheEvict(cacheNames = "pacientes", allEntries = true, beforeInvocation = true)},
        put = {@CachePut(cacheNames = "paciente", key = "#paciente.id")}
    )
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(new PacienteSchema(paciente)).toEntity();
    }

    @Override
    @Cacheable(value = "paciente", key = "#id")
    public Optional<Paciente> findById(UUID id) {
        return pacienteRepository.findById(id).map(PacienteSchema::toEntity);
    }

    @Override
    @Cacheable(value = "pacientes", key = "'pacientes'")
    public List<Paciente> findAll() {
        return pacienteRepository.findAll().stream().map(PacienteSchema::toEntity).toList();
    }

    @Override
    @Caching(
            evict = {@CacheEvict(cacheNames = "pacientes", allEntries = true, beforeInvocation = true)},
            put = {@CachePut(cacheNames = "paciente", key = "#paciente.id")}
    )
    public Paciente update(Paciente paciente) {
        return pacienteRepository.save(new PacienteSchema(paciente)).toEntity();
    }
}