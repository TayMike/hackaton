package com.fiap.hackaton.infrastructure.insumo.gateway;

import com.fiap.hackaton.entity.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.infrastructure.config.db.repository.InsumoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.InsumoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class InsumoDatabaseGateway implements InsumoGateway {

    private final InsumoRepository insumoRepository;

    public InsumoDatabaseGateway(InsumoRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    @Override
    public Insumo save(Insumo insumo) {
        InsumoSchema saved = insumoRepository.save(new InsumoSchema(insumo));
        return saved.toInsumo();
    }

    @Override
    public Optional<InsumoSchema> findById(UUID id) {
        return insumoRepository.findById(id);
    }

    @Override
    public List<InsumoSchema> findAll() {
        return insumoRepository.findAll().stream().toList();
    }

    @Override
    public Insumo update(Insumo insumo) {
        InsumoSchema updated = insumoRepository.save(new InsumoSchema(insumo));
        return updated.toInsumo();
    }

    @Override
    public void deleteById(UUID id) {
        insumoRepository.deleteById(id);
    }
}