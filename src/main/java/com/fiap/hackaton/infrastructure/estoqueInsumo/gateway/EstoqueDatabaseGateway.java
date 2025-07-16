package com.fiap.hackaton.infrastructure.estoqueInsumo.gateway;

import com.fiap.hackaton.entity.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.infrastructure.config.db.repository.EstoqueInsumoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.EstoqueInsumoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class EstoqueDatabaseGateway implements EstoqueInsumoGateway {

    private final EstoqueInsumoRepository estoqueRepository;

    public EstoqueDatabaseGateway(EstoqueInsumoRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    @Override
    public EstoqueInsumo save(EstoqueInsumo estoqueInsumo) {
        EstoqueInsumoSchema schema = new EstoqueInsumoSchema(estoqueInsumo);
        return estoqueRepository.save(schema).toEstoqueInsumo();
    }

    @Override
    public Optional<EstoqueInsumo> findById(UUID id) {
        return estoqueRepository.findById(id).map(EstoqueInsumoSchema::toEstoqueInsumo);
    }

    @Override
    public List<EstoqueInsumo> findAll() {
        return estoqueRepository.findAll()
                .stream()
                .map(EstoqueInsumoSchema::toEstoqueInsumo)
                .collect(Collectors.toList());
    }

    @Override
    public EstoqueInsumo update(EstoqueInsumo estoqueInsumo) {
        EstoqueInsumoSchema schema = new EstoqueInsumoSchema(estoqueInsumo);
        return estoqueRepository.save(schema).toEstoqueInsumo();
    }

    @Override
    public void deleteById(UUID id) {
        estoqueRepository.deleteById(id);
    }
}
