package com.fiap.hackaton.infrastructure.estoqueInsumo.gateway;

import com.fiap.hackaton.entity.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.infrastructure.config.db.repository.EstoqueInsumoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.EstoqueInsumoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.InsumoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EstoqueInsumoDatabaseGateway implements EstoqueInsumoGateway {

    private final EstoqueInsumoRepository estoqueInsumoRepository;

    public EstoqueInsumoDatabaseGateway(EstoqueInsumoRepository estoqueInsumoRepository) {
        this.estoqueInsumoRepository = estoqueInsumoRepository;
    }

    @Override
    public EstoqueInsumoSchema save(EstoqueInsumo estoqueInsumo, List<InsumoSchema> itens, HospitalSchema hospitalSchema) {
        EstoqueInsumoSchema schema = new EstoqueInsumoSchema(estoqueInsumo, itens, hospitalSchema);
        return estoqueInsumoRepository.save(schema);
    }

    @Override
    public Optional<EstoqueInsumoSchema> findById(UUID id) {
        return estoqueInsumoRepository.findById(id);
    }

    @Override
    public List<EstoqueInsumoSchema> findAll() {
        return estoqueInsumoRepository.findAll();
    }

    @Override
    public EstoqueInsumoSchema update(EstoqueInsumo estoqueInsumo, List<InsumoSchema> itens, HospitalSchema hospitalSchema) {
        EstoqueInsumoSchema schema = new EstoqueInsumoSchema(estoqueInsumo, itens, hospitalSchema);
        return estoqueInsumoRepository.save(schema);
    }

    @Override
    public void deleteById(UUID id) {
        estoqueInsumoRepository.deleteById(id);
    }
}
