package com.fiap.hackaton.entity.insumo.gateway;

import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.infrastructure.config.db.schema.InsumoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InsumoGateway {
    Insumo save(Insumo insumo);
    Optional<InsumoSchema> findById(UUID id);
    List<InsumoSchema> findAll();
    Insumo update(Insumo insumo);
    void deleteById(UUID id);
}