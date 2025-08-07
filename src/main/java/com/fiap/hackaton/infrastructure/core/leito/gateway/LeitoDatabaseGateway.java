package com.fiap.hackaton.infrastructure.core.leito.gateway;

import com.fiap.hackaton.entity.core.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.core.leito.model.Leito;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.LeitoRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.LeitoSchema;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LeitoDatabaseGateway implements LeitoGateway {

    private final LeitoRepository leitoRepository;

    public LeitoDatabaseGateway(LeitoRepository leitoRepository) {
        this.leitoRepository = leitoRepository;
    }

    @Override
    @Caching(
            evict = {@CacheEvict(cacheNames = "leitos", allEntries = true, beforeInvocation = true)},
            put = {@CachePut(cacheNames = "leito", key = "#leito.id")}
    )
    public Leito save(Leito leito) {
        return leitoRepository.save(new LeitoSchema(leito)).toEntity();
    }

    @Override
    @Cacheable(value = "leito", key = "#id")
    public Optional<Leito> findById(UUID id) {
        return leitoRepository.findById(id).map(LeitoSchema::toEntity);
    }

    @Override
    @Cacheable(value = "leitos", key = "'leitos'")
    public List<Leito> findAll() {
        return leitoRepository.findAll().stream().map(LeitoSchema::toEntity).toList();
    }

    @Override
    @Caching(
            evict = {@CacheEvict(cacheNames = "leitos", allEntries = true, beforeInvocation = true)},
            put = {@CachePut(cacheNames = "leito", key = "#leito.id")}
    )
    public Leito update(Leito leito) {
        return leitoRepository.save(new LeitoSchema(leito)).toEntity();
    }

    @Override
    @CacheEvict(cacheNames = {"leito", "leitos"}, allEntries = true, beforeInvocation = true)
    public void deleteById(UUID id) {
        leitoRepository.deleteById(id);
    }

}
