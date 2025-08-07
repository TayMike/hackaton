package com.fiap.hackaton.infrastructure.core.colaborador.gateway;

import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.ColaboradorRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.ColaboradorSchema;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ColaboradorDatabaseGateway implements ColaboradorGateway {

    private final ColaboradorRepository colaboradorRepository;

    public ColaboradorDatabaseGateway(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    @Override
    @Caching(
        evict = {@CacheEvict(cacheNames = "colaboradores", allEntries = true, beforeInvocation = true)},
        put = {@CachePut(cacheNames = "colaborador", key = "#colaborador.id")}
    )
    public Colaborador save(Colaborador colaborador) {
        return this.colaboradorRepository.save(new ColaboradorSchema(colaborador)).toEntity();
    }

    @Override
    @Caching(
            evict = {@CacheEvict(cacheNames = "colaboradores", allEntries = true, beforeInvocation = true)},
            put = {@CachePut(cacheNames = "colaborador", key = "#colaborador.id")}
    )
    public Colaborador update(Colaborador colaborador) {
        return this.colaboradorRepository.save(new ColaboradorSchema(colaborador)).toEntity();
    }

    @Override
    @Cacheable(value = "colaborador", key = "#id")
    public Optional<Colaborador> findById(UUID id) {
        return colaboradorRepository.findById(id)
                .map(ColaboradorSchema::toEntity);
    }

    @Override
    @Cacheable(value = "colaboradores", key = "'colaboradores'")
    public List<Colaborador> findAll() {
        return colaboradorRepository.findAll().stream().map(ColaboradorSchema::toEntity).toList();
    }
}