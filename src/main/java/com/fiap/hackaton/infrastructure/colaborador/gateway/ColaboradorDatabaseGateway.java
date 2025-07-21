package com.fiap.hackaton.infrastructure.colaborador.gateway;

import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.infrastructure.config.db.repository.ColaboradorRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ColaboradorDatabaseGateway implements ColaboradorGateway {

    private final ColaboradorRepository colaboradorRepository;

    public ColaboradorDatabaseGateway(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    @Override
    public ColaboradorSchema save(Colaborador colaborador, HospitalSchema hospital) {
        return this.colaboradorRepository.save(new ColaboradorSchema(colaborador, hospital));
    }

    @Override
    public ColaboradorSchema update(Colaborador colaborador, HospitalSchema hospital) {
        return this.colaboradorRepository.save(new ColaboradorSchema(colaborador, hospital));
    }

    @Override
    public Optional<ColaboradorSchema> findById(UUID id) {
        return colaboradorRepository
                .findById(id);
    }

    @Override
    public List<ColaboradorSchema> findAll() {
        return colaboradorRepository
                .findAll().stream().toList();
    }
}