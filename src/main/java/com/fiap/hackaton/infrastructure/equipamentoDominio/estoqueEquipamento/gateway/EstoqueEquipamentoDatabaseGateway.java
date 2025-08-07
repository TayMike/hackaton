package com.fiap.hackaton.infrastructure.equipamentoDominio.estoqueEquipamento.gateway;

import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.EstoqueEquipamentoRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.EstoqueEquipamentoSchema;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EstoqueEquipamentoDatabaseGateway implements EstoqueEquipamentoGateway {

    private final EstoqueEquipamentoRepository estoqueEquipamentoRepository;

    public EstoqueEquipamentoDatabaseGateway(EstoqueEquipamentoRepository estoqueEquipamentoRepository) {
        this.estoqueEquipamentoRepository = estoqueEquipamentoRepository;
    }

    @Override
    @Caching(
        evict = {@CacheEvict(cacheNames = "estoqueEquipamentos", allEntries = true, beforeInvocation = true)},
        put = {@CachePut(cacheNames = "estoqueEquipamento", key = "#estoqueEquipamento.id")}
    )
    public EstoqueEquipamento save(EstoqueEquipamento estoqueEquipamento) {
        return this.estoqueEquipamentoRepository.save(new EstoqueEquipamentoSchema(estoqueEquipamento)).toEntity();
    }

    @Override
    @Cacheable(value = "estoqueEquipamento", key = "#id")
    public Optional<EstoqueEquipamento> findById(UUID id) {
        return estoqueEquipamentoRepository.findById(id).map(EstoqueEquipamentoSchema::toEntity);
    }

    @Override
    @Cacheable(value = "estoqueEquipamentos", key = "'estoqueEquipamentos'")
    public List<EstoqueEquipamento> findAll() {
        return estoqueEquipamentoRepository.findAll().stream().map(EstoqueEquipamentoSchema::toEntity).toList();
    }

    @Override
    @Caching(
            evict = {@CacheEvict(cacheNames = "estoqueEquipamentos", allEntries = true, beforeInvocation = true)},
            put = {@CachePut(cacheNames = "estoqueEquipamento", key = "#estoqueEquipamento.id")}
    )
    public EstoqueEquipamento update(EstoqueEquipamento estoqueEquipamento) {
        return estoqueEquipamentoRepository.save(new EstoqueEquipamentoSchema(estoqueEquipamento)).toEntity();
    }

    @Override
    @CacheEvict(cacheNames = {"estoqueEquipamento", "estoqueEquipamentos"}, allEntries = true, beforeInvocation = true)
    public void deleteById(UUID id) {
        estoqueEquipamentoRepository.deleteById(id);
    }
}
