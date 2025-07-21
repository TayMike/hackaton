package com.fiap.hackaton.infrastructure.equipamento.gateway;

import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.infrastructure.config.db.repository.EquipamentoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EquipamentoDatabaseGateway implements EquipamentoGateway {

    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoDatabaseGateway(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    @Override
    public EquipamentoSchema save(Equipamento equipamento, HospitalSchema hospitalSchema) {
        return equipamentoRepository.save(new EquipamentoSchema(equipamento, hospitalSchema));
    }

    @Override
    public Optional<EquipamentoSchema> findById(UUID id) {
        return equipamentoRepository.findById(id);
    }

    @Override
    public List<EquipamentoSchema> findAll() {
        return equipamentoRepository.findAll();
    }

    @Override
    public EquipamentoSchema update(Equipamento equipamento, HospitalSchema hospitalSchema) {
        return equipamentoRepository.save(new EquipamentoSchema(equipamento, hospitalSchema));
    }

    @Override
    public void deleteById(UUID id) {
        equipamentoRepository.deleteById(id);
    }

}