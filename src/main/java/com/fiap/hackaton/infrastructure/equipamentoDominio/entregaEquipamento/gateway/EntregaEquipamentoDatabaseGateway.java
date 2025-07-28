package com.fiap.hackaton.infrastructure.equipamentoDominio.entregaEquipamento.gateway;

import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.gateway.EntregaEquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.model.EntregaEquipamento;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.EntregaEquipamentoRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.EntregaEquipamentoSchema;

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
        return entregaEquipamentoRepository.save(new EntregaEquipamentoSchema(entregaEquipamento)).toEntity();
    }

    @Override
    public Optional<EntregaEquipamento> findById(UUID id) {
        return entregaEquipamentoRepository.findById(id)
                .map(EntregaEquipamentoSchema::toEntity);
    }

    @Override
    public List<EntregaEquipamento> findAll() {
        return entregaEquipamentoRepository.findAll()
                .stream()
                .map(EntregaEquipamentoSchema::toEntity)
                .toList();
    }

}
