package com.fiap.hackaton.infrastructure.entregaInsumo.gateway;

import com.fiap.hackaton.entity.entregaInsumo.gateway.EntregaInsumoGateway;
import com.fiap.hackaton.entity.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.infrastructure.config.db.repository.EntregaInsumoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.EntregaInsumoSchema;

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
        EntregaInsumoSchema saved = entregaInsumoRepository.save(new EntregaInsumoSchema(entregaInsumo));
        return saved.toEntrega();
    }

    @Override
    public Optional<EntregaInsumo> findById(UUID id) {
        return entregaInsumoRepository.findById(id)
                .map(EntregaInsumoSchema::toEntrega);
    }

    @Override
    public List<EntregaInsumo> findAll() {
        return entregaInsumoRepository.findAll()
                .stream()
                .map(EntregaInsumoSchema::toEntrega)
                .toList();
    }
}