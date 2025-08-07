package com.fiap.hackaton.infrastructure.insumoDominio.estoqueInsumo.gateway;

import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.EstoqueInsumoRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.EstoqueInsumoSchema;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EstoqueInsumoDatabaseGateway implements EstoqueInsumoGateway {

    private final EstoqueInsumoRepository estoqueInsumoRepository;

    public EstoqueInsumoDatabaseGateway(EstoqueInsumoRepository estoqueInsumoRepository) {
        this.estoqueInsumoRepository = estoqueInsumoRepository;
    }

    @Override
    @CacheEvict(cacheNames = "estoqueInsumos", allEntries = true, beforeInvocation = true)
    public EstoqueInsumo save(EstoqueInsumo estoqueInsumo) {
        return estoqueInsumoRepository.save(new EstoqueInsumoSchema(estoqueInsumo)).toEntity();
    }

    @Override
    @Cacheable(value = "estoqueInsumos", key = "'estoqueInsumo'")
    public List<EstoqueInsumo> findAll() {
        return estoqueInsumoRepository.findAll().stream().map(EstoqueInsumoSchema::toEntity).toList();
    }

    @Override
    @Cacheable(value = "estoqueHospital", key = "#id")
    public Optional<EstoqueInsumo> findByHospitalId(UUID hospitalId){
        return estoqueInsumoRepository.findByHospitalId(hospitalId)
                .map(EstoqueInsumoSchema::toEntity);
    }
}
