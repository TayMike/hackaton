package com.fiap.hackaton.infrastructure.insumoDominio.insumo.gateway;

import com.fiap.hackaton.entity.insumoDominio.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.InsumoRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.InsumoSchema;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InsumoDatabaseGateway implements InsumoGateway {

    private final InsumoRepository insumoRepository;

    public InsumoDatabaseGateway(InsumoRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    @Override
    @Caching(
        evict = {@CacheEvict(cacheNames = "insumos", allEntries = true, beforeInvocation = true)},
        put = {@CachePut(cacheNames = "insumo", key = "#insumo.id")}
    )
    public Insumo save(Insumo insumo) {
        return insumoRepository.save(new InsumoSchema(insumo)).toEntity();
    }

    @Override
    @Cacheable(value = "insumo", key = "#id")
    public Optional<Insumo> findById(UUID id) {
        return insumoRepository.findById(id).map(InsumoSchema::toEntity);
    }

    @Override
    @Cacheable(value = "insumos", key = "'insumos'")
    public List<Insumo> findAll() {
        return insumoRepository.findAll().stream().map(InsumoSchema::toEntity).toList();
    }

    @Override
    @Caching(
            evict = {@CacheEvict(cacheNames = "insumos", allEntries = true, beforeInvocation = true)},
            put = {@CachePut(cacheNames = "insumo", key = "#insumo.id")}
    )
    public Insumo update(Insumo insumo) {
        return insumoRepository.save(new InsumoSchema(insumo)).toEntity();
    }

    @Override
    @CacheEvict(cacheNames = {"insumo", "insumos"}, allEntries = true, beforeInvocation = true)
    public void deleteById(UUID id) {
        insumoRepository.deleteById(id);
    }
}