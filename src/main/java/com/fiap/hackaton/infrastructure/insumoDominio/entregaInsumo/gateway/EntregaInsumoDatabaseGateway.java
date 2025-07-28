package com.fiap.hackaton.infrastructure.insumoDominio.entregaInsumo.gateway;

import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.gateway.EntregaInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.EntregaInsumoRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.EntregaInsumoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EntregaInsumoDatabaseGateway implements EntregaInsumoGateway {

    private final EntregaInsumoRepository entregaInsumoRepository;

    public EntregaInsumoDatabaseGateway(EntregaInsumoRepository entregaInsumoRepository) {
        this.entregaInsumoRepository = entregaInsumoRepository;
    }

    @Override
    public EntregaInsumo save(EntregaInsumo entregaInsumo) {
        return entregaInsumoRepository.save(new EntregaInsumoSchema(entregaInsumo)).toEntity();
    }

    @Override
    public Optional<EntregaInsumo> findById(UUID id) {
        return entregaInsumoRepository.findById(id)
                .map(EntregaInsumoSchema::toEntity);
    }

    @Override
    public List<EntregaInsumo> findAll() {
        return entregaInsumoRepository.findAll()
                .stream()
                .map(EntregaInsumoSchema::toEntity)
                .toList();
    }
}