package com.fiap.hackaton.infrastructure.equipamentoDominio.equipamento.gateway;

import com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.EquipamentoRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.EquipamentoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EquipamentoDatabaseGateway implements EquipamentoGateway {

    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoDatabaseGateway(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    @Override
    public Equipamento save(Equipamento equipamento) {
        return equipamentoRepository.save(new EquipamentoSchema(equipamento)).toEntity();
    }

    @Override
    public Optional<Equipamento> findById(UUID id) {
        return equipamentoRepository.findById(id).map(EquipamentoSchema::toEntity);
    }

    @Override
    public List<Equipamento> findAll() {
        return equipamentoRepository.findAll().stream().map(EquipamentoSchema::toEntity).toList();
    }

    @Override
    public Equipamento update(Equipamento equipamento) {
        return equipamentoRepository.save(new EquipamentoSchema(equipamento)).toEntity();
    }

    @Override
    public void deleteById(UUID id) {
        equipamentoRepository.deleteById(id);
    }

}