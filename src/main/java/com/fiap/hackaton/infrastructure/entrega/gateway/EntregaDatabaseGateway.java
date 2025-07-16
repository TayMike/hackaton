package com.fiap.hackaton.infrastructure.entrega.gateway;

import com.fiap.hackaton.entity.entrega.gateway.EntregaGateway;
import com.fiap.hackaton.entity.entrega.model.Entrega;
import com.fiap.hackaton.infrastructure.config.db.repository.EntregaRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.EntregaSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EntregaDatabaseGateway implements EntregaGateway {

    private final EntregaRepository entregaRepository;

    public EntregaDatabaseGateway(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    @Override
    public Entrega save(Entrega entrega) {
        EntregaSchema saved = entregaRepository.save(new EntregaSchema(entrega));
        return saved.toEntrega();
    }

    @Override
    public Optional<Entrega> findById(UUID id) {
        return entregaRepository.findById(id)
                .map(EntregaSchema::toEntrega);
    }

    @Override
    public List<Entrega> findAll() {
        return entregaRepository.findAll()
                .stream()
                .map(EntregaSchema::toEntrega)
                .toList();
    }
}