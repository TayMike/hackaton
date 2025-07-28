package com.fiap.hackaton.infrastructure.insumoDominio.estoqueInsumo.gateway;

import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.EstoqueInsumoRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.EstoqueInsumoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EstoqueInsumoDatabaseGateway implements EstoqueInsumoGateway {

    private final EstoqueInsumoRepository estoqueInsumoRepository;

    public EstoqueInsumoDatabaseGateway(EstoqueInsumoRepository estoqueInsumoRepository) {
        this.estoqueInsumoRepository = estoqueInsumoRepository;
    }

    @Override
    public EstoqueInsumo save(EstoqueInsumo estoqueInsumo) {
        return estoqueInsumoRepository.save(new EstoqueInsumoSchema(estoqueInsumo)).toEntity();
    }

    @Override
    public List<EstoqueInsumo> findAll() {
        return estoqueInsumoRepository.findAll().stream().map(EstoqueInsumoSchema::toEntity).toList();
    }

    @Override
    public Optional<EstoqueInsumo> findByHospitalId(UUID hospitalId){
        return estoqueInsumoRepository.findByHospitalId(hospitalId)
                .map(EstoqueInsumoSchema::toEntity);
    }
}
