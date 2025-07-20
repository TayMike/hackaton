package com.fiap.hackaton.infrastructure.coletaEquipamento.gateway;

import com.fiap.hackaton.entity.coletaEquipamento.gateway.ColetaEquipamentoGateway;
import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;
import com.fiap.hackaton.infrastructure.config.db.repository.ColetaEquipamentoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.ColetaEquipamentoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ColetaEquipamentoDatabaseGateway implements ColetaEquipamentoGateway {

    private final ColetaEquipamentoRepository coletaEquipamentoRepository;

    public ColetaEquipamentoDatabaseGateway(ColetaEquipamentoRepository coletaEquipamentoRepository) {
        this.coletaEquipamentoRepository = coletaEquipamentoRepository;
    }

    @Override
    public ColetaEquipamento save(ColetaEquipamento coletaEquipamento) {
        ColetaEquipamentoSchema saved = coletaEquipamentoRepository.save(new ColetaEquipamentoSchema(coletaEquipamento));
        return saved.toColetaEquipamento();
    }

    @Override
    public Optional<ColetaEquipamento> findById(UUID id) {
        return coletaEquipamentoRepository.findById(id)
                .map(ColetaEquipamentoSchema::toColetaEquipamento);
    }

    @Override
    public List<ColetaEquipamento> findAll() {
        return coletaEquipamentoRepository.findAll()
                .stream()
                .map(ColetaEquipamentoSchema::toColetaEquipamento)
                .toList();
    }
}