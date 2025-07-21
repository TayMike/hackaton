package com.fiap.hackaton.infrastructure.estoqueEquipamento.gateway;

import com.fiap.hackaton.entity.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.infrastructure.config.db.repository.EstoqueEquipamentoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EstoqueEquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EstoqueEquipamentoDatabaseGateway implements EstoqueEquipamentoGateway {

    private final EstoqueEquipamentoRepository estoqueEquipamentoRepository;

    public EstoqueEquipamentoDatabaseGateway(EstoqueEquipamentoRepository estoqueEquipamentoRepository) {
        this.estoqueEquipamentoRepository = estoqueEquipamentoRepository;
    }

    @Override
    public EstoqueEquipamentoSchema save(EstoqueEquipamento estoqueEquipamento, List<EquipamentoSchema> equipamentos, HospitalSchema hospitalSchema) {
        EstoqueEquipamentoSchema schema = new EstoqueEquipamentoSchema(estoqueEquipamento, equipamentos, hospitalSchema);
        return estoqueEquipamentoRepository.save(schema);
    }

    @Override
    public Optional<EstoqueEquipamentoSchema> findById(UUID id) {
        return estoqueEquipamentoRepository.findById(id);
    }

    @Override
    public List<EstoqueEquipamentoSchema> findAll() {
        return estoqueEquipamentoRepository.findAll();
    }

    @Override
    public EstoqueEquipamentoSchema update(EstoqueEquipamento estoqueEquipamento, List<EquipamentoSchema> equipamentos, HospitalSchema hospitalSchema) {
        EstoqueEquipamentoSchema schema = new EstoqueEquipamentoSchema(estoqueEquipamento, equipamentos, hospitalSchema);
        return estoqueEquipamentoRepository.save(schema);
    }

    @Override
    public void deleteById(UUID id) {
        estoqueEquipamentoRepository.deleteById(id);
    }
}
