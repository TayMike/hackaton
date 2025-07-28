package com.fiap.hackaton.infrastructure.equipamentoDominio.estoqueEquipamento.gateway;

import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.EstoqueEquipamentoRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.EstoqueEquipamentoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EstoqueEquipamentoDatabaseGateway implements EstoqueEquipamentoGateway {

    private final EstoqueEquipamentoRepository estoqueEquipamentoRepository;

    public EstoqueEquipamentoDatabaseGateway(EstoqueEquipamentoRepository estoqueEquipamentoRepository) {
        this.estoqueEquipamentoRepository = estoqueEquipamentoRepository;
    }

    @Override
    public EstoqueEquipamento save(EstoqueEquipamento estoqueEquipamento) {
        return this.estoqueEquipamentoRepository.save(new EstoqueEquipamentoSchema(estoqueEquipamento)).toEntity();
    }

    @Override
    public Optional<EstoqueEquipamento> findById(UUID id) {
        return estoqueEquipamentoRepository.findById(id).map(EstoqueEquipamentoSchema::toEntity);
    }

    @Override
    public List<EstoqueEquipamento> findAll() {
        return estoqueEquipamentoRepository.findAll().stream().map(EstoqueEquipamentoSchema::toEntity).toList();
    }

    @Override
    public EstoqueEquipamento update(EstoqueEquipamento estoqueEquipamento) {
        return estoqueEquipamentoRepository.save(new EstoqueEquipamentoSchema(estoqueEquipamento)).toEntity();
    }

    @Override
    public void deleteById(UUID id) {
        estoqueEquipamentoRepository.deleteById(id);
    }
}
