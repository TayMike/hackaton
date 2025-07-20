package com.fiap.hackaton.infrastructure.entregaEquipamento.gateway;

import com.fiap.hackaton.entity.entregaEquipamento.gateway.EntregaEquipamentoGateway;
import com.fiap.hackaton.entity.entregaEquipamento.model.EntregaEquipamento;
import com.fiap.hackaton.infrastructure.config.db.repository.EntregaEquipamentoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.EntregaEquipamentoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EntregaEquipamentoDatabaseGateway implements EntregaEquipamentoGateway {

    private final EntregaEquipamentoRepository entregaEquipamentoRepository;

    public EntregaEquipamentoDatabaseGateway(EntregaEquipamentoRepository entregaEquipamentoRepository) {
        this.entregaEquipamentoRepository = entregaEquipamentoRepository;
    }

    @Override
    public EntregaEquipamento save(EntregaEquipamento entregaEquipamento) {
        EntregaEquipamentoSchema saved = entregaEquipamentoRepository.save(new EntregaEquipamentoSchema(entregaEquipamento));
        return saved.toEntregaEquipamento();
    }

    @Override
    public Optional<EntregaEquipamento> findById(UUID id) {
        return entregaEquipamentoRepository.findById(id)
                .map(EntregaEquipamentoSchema::toEntregaEquipamento);
    }

    @Override
    public List<EntregaEquipamento> findAll() {
        return entregaEquipamentoRepository.findAll()
                .stream()
                .map(EntregaEquipamentoSchema::toEntregaEquipamento)
                .toList();
    }

}
