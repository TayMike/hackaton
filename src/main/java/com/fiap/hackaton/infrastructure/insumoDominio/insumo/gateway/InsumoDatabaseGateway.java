package com.fiap.hackaton.infrastructure.insumoDominio.insumo.gateway;

import com.fiap.hackaton.entity.insumoDominio.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.InsumoRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.InsumoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InsumoDatabaseGateway implements InsumoGateway {

    private final InsumoRepository insumoRepository;

    public InsumoDatabaseGateway(InsumoRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    @Override
    public Insumo save(Insumo insumo) {
        return insumoRepository.save(new InsumoSchema(insumo)).toEntity();
    }

    @Override
    public Optional<Insumo> findById(UUID id) {
        return insumoRepository.findById(id).map(InsumoSchema::toEntity);
    }

    @Override
    public List<Insumo> findAll() {
        return insumoRepository.findAll().stream().map(InsumoSchema::toEntity).toList();
    }

    @Override
    public Insumo update(Insumo insumo) {
        return insumoRepository.save(new InsumoSchema(insumo)).toEntity();
    }

    @Override
    public void deleteById(UUID id) {
        insumoRepository.deleteById(id);
    }
}