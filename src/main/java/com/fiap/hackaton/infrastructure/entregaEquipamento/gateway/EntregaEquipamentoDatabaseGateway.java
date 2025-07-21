package com.fiap.hackaton.infrastructure.entregaEquipamento.gateway;

import com.fiap.hackaton.entity.entregaEquipamento.gateway.EntregaEquipamentoGateway;
import com.fiap.hackaton.entity.entregaEquipamento.model.EntregaEquipamento;
import com.fiap.hackaton.infrastructure.config.db.repository.EntregaEquipamentoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EntregaEquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EntregaEquipamentoDatabaseGateway implements EntregaEquipamentoGateway {

    private final EntregaEquipamentoRepository entregaEquipamentoRepository;

    public EntregaEquipamentoDatabaseGateway(EntregaEquipamentoRepository entregaEquipamentoRepository) {
        this.entregaEquipamentoRepository = entregaEquipamentoRepository;
    }

    @Override
    public EntregaEquipamento save(EntregaEquipamento entregaEquipamento, List<EquipamentoSchema> equipamentos, ColaboradorSchema colaboradorRecebedorSchema, HospitalSchema hospitalSchema) {
        EntregaEquipamentoSchema saved = entregaEquipamentoRepository.save(new EntregaEquipamentoSchema(entregaEquipamento, equipamentos, colaboradorRecebedorSchema, hospitalSchema));
        return saved.toEntregaEquipamento();
    }

    @Override
    public Optional<EntregaEquipamento> findById(UUID id) {
        return entregaEquipamentoRepository.findById(id)
                .map(EntregaEquipamentoSchema::toEntregaEquipamento);
    }

    @Override
    public List<EntregaEquipamento> findAll() {
        return entregaEquipamentoRepository.findAll()
                .stream()
                .map(EntregaEquipamentoSchema::toEntregaEquipamento)
                .toList();
    }

}
