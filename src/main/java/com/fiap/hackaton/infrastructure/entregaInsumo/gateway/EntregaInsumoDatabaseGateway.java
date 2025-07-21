package com.fiap.hackaton.infrastructure.entregaInsumo.gateway;

import com.fiap.hackaton.entity.entregaInsumo.gateway.EntregaInsumoGateway;
import com.fiap.hackaton.entity.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.infrastructure.config.db.repository.EntregaInsumoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EntregaInsumoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.InsumoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EntregaInsumoDatabaseGateway implements EntregaInsumoGateway {

    private final EntregaInsumoRepository entregaInsumoRepository;

    public EntregaInsumoDatabaseGateway(EntregaInsumoRepository entregaInsumoRepository) {
        this.entregaInsumoRepository = entregaInsumoRepository;
    }

    @Override
    public EntregaInsumo save(EntregaInsumo entregaInsumo, List<InsumoSchema> insumos, ColaboradorSchema colaboradorRecebedorSchema, HospitalSchema hospitalSchema) {
        EntregaInsumoSchema saved = entregaInsumoRepository.save(new EntregaInsumoSchema(entregaInsumo, insumos, colaboradorRecebedorSchema, hospitalSchema));
        return saved.toEntrega();
    }

    @Override
    public Optional<EntregaInsumo> findById(UUID id) {
        return entregaInsumoRepository.findById(id)
                .map(EntregaInsumoSchema::toEntrega);
    }

    @Override
    public List<EntregaInsumo> findAll() {
        return entregaInsumoRepository.findAll()
                .stream()
                .map(EntregaInsumoSchema::toEntrega)
                .toList();
    }
}