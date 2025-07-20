package com.fiap.hackaton.infrastructure.descarteEquipamento.gateway;

import com.fiap.hackaton.entity.descarteEquipamento.gateway.DescarteEquipamentoGateway;
import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;
import com.fiap.hackaton.infrastructure.config.db.repository.DescarteEquipamentoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.DescarteEquipamentoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DescarteEquipamentoDatabaseGateway implements DescarteEquipamentoGateway {

    private final DescarteEquipamentoRepository descarteEquipamentoRepository;

    public DescarteEquipamentoDatabaseGateway(DescarteEquipamentoRepository descarteEquipamentoRepository) {
        this.descarteEquipamentoRepository = descarteEquipamentoRepository;
    }

    @Override
    public DescarteEquipamento save(DescarteEquipamento descarteEquipamento) {
        DescarteEquipamentoSchema saved = descarteEquipamentoRepository.save(new DescarteEquipamentoSchema(descarteEquipamento));
        return saved.toDescarteEquipamento();
    }

    @Override
    public Optional<DescarteEquipamento> findById(UUID id) {
        return descarteEquipamentoRepository.findById(id)
                .map(DescarteEquipamentoSchema::toDescarteEquipamento);
    }

    @Override
    public List<DescarteEquipamento> findAll() {
        return descarteEquipamentoRepository.findAll()
                .stream()
                .map(DescarteEquipamentoSchema::toDescarteEquipamento)
                .toList();
    }
}
