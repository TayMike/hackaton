package com.fiap.hackaton.infrastructure.estoqueEquipamento.gateway;

import com.fiap.hackaton.entity.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.infrastructure.config.db.repository.EstoqueEquipamentoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.EstoqueEquipamentoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class EstoqueEquipamentoDatabaseGateway implements EstoqueEquipamentoGateway {

    private final EstoqueEquipamentoRepository estoqueEquipamentoRepository;

    public EstoqueEquipamentoDatabaseGateway(EstoqueEquipamentoRepository estoqueEquipamentoRepository) {
        this.estoqueEquipamentoRepository = estoqueEquipamentoRepository;
    }

    @Override
    public EstoqueEquipamento save(EstoqueEquipamento estoqueEquipamento) {
        EstoqueEquipamentoSchema schema = new EstoqueEquipamentoSchema(estoqueEquipamento);
        return estoqueEquipamentoRepository.save(schema).toEstoqueEquipamento();
    }

    @Override
    public Optional<EstoqueEquipamento> findById(UUID id) {
        return estoqueEquipamentoRepository.findById(id).map(EstoqueEquipamentoSchema::toEstoqueEquipamento);
    }

    @Override
    public List<EstoqueEquipamento> findAll() {
        return estoqueEquipamentoRepository.findAll()
                .stream()
                .map(EstoqueEquipamentoSchema::toEstoqueEquipamento)
                .collect(Collectors.toList());
    }

    @Override
    public EstoqueEquipamento update(EstoqueEquipamento estoqueEquipamento) {
        EstoqueEquipamentoSchema schema = new EstoqueEquipamentoSchema(estoqueEquipamento);
        return estoqueEquipamentoRepository.save(schema).toEstoqueEquipamento();
    }

    @Override
    public void deleteById(UUID id) {
        estoqueEquipamentoRepository.deleteById(id);
    }
}
