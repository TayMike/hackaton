package com.fiap.hackaton.infrastructure.equipamento.gateway;

import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.infrastructure.config.db.repository.EquipamentoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class EquipamentoDatabaseGateway implements EquipamentoGateway {

    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoDatabaseGateway(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    @Override
    public Equipamento save(Equipamento equipamento) {
        EquipamentoSchema saved = equipamentoRepository.save(new EquipamentoSchema(equipamento));
        return saved.toEquipamento();
    }

    @Override
    public Optional<Equipamento> findById(UUID id) {
        return equipamentoRepository.findById(id).map(EquipamentoSchema::toEquipamento);
    }

    @Override
    public List<Equipamento> findAll() {
        return equipamentoRepository.findAll()
                .stream()
                .map(EquipamentoSchema::toEquipamento)
                .collect(Collectors.toList());
    }

    @Override
    public Equipamento update(Equipamento equipamento) {
        EquipamentoSchema updated = equipamentoRepository.save(new EquipamentoSchema(equipamento));
        return updated.toEquipamento();
    }

    @Override
    public void deleteById(UUID id) {
        equipamentoRepository.deleteById(id);
    }

}