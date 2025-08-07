package com.fiap.hackaton.infrastructure.equipamentoDominio.equipamento.gateway;

import com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.EquipamentoRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.EquipamentoSchema;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EquipamentoDatabaseGateway implements EquipamentoGateway {

    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoDatabaseGateway(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    @Override
    @Caching(
        evict = {@CacheEvict(cacheNames = "equipamentos", allEntries = true, beforeInvocation = true)},
        put = {@CachePut(cacheNames = "equipamento", key = "#equipamento.id")}
    )
    public Equipamento save(Equipamento equipamento) {
        return equipamentoRepository.save(new EquipamentoSchema(equipamento)).toEntity();
    }

    @Override
    @Cacheable(value = "equipamento", key = "#id")
    public Optional<Equipamento> findById(UUID id) {
        return equipamentoRepository.findById(id).map(EquipamentoSchema::toEntity);
    }

    @Override
    @Cacheable(value = "equipamentos", key = "'equipamentos'")
    public List<Equipamento> findAll() {
        return equipamentoRepository.findAll().stream().map(EquipamentoSchema::toEntity).toList();
    }

    @Override
    @Caching(
            evict = {@CacheEvict(cacheNames = "equipamentos", allEntries = true, beforeInvocation = true)},
            put = {@CachePut(cacheNames = "equipamento", key = "#equipamento.id")}
    )
    public Equipamento update(Equipamento equipamento) {
        return equipamentoRepository.save(new EquipamentoSchema(equipamento)).toEntity();
    }

    @Override
    @CacheEvict(cacheNames = {"equipamento", "equipamentos"}, allEntries = true, beforeInvocation = true)
    public void deleteById(UUID id) {
        equipamentoRepository.deleteById(id);
    }

}