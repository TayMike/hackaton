package com.fiap.hackaton.entity.estoqueInsumo.gateway;

import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.infrastructure.config.db.schema.EstoqueInsumoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.InsumoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EstoqueInsumoGateway {
    EstoqueInsumoSchema save(EstoqueInsumo estoqueInsumo, List<InsumoSchema> itens, HospitalSchema hospitalSchema);
    Optional<EstoqueInsumoSchema> findById(UUID id);
    List<EstoqueInsumoSchema> findAll();
    EstoqueInsumoSchema update(EstoqueInsumo estoqueInsumo, List<InsumoSchema> itens, HospitalSchema hospitalSchema);
    void deleteById(UUID id);
}