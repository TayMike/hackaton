package com.fiap.hackaton.usecase.estoqueInsumo;

import com.fiap.hackaton.entity.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.infrastructure.config.db.schema.EstoqueInsumoSchema;

import java.util.List;

public class SearchEstoqueInsumoUseCase {

    private final EstoqueInsumoGateway estoqueGateway;

    public SearchEstoqueInsumoUseCase(EstoqueInsumoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public List<EstoqueInsumo> execute() {
        return this.estoqueGateway.findAll().stream().map(EstoqueInsumoSchema::toEstoqueInsumo).toList();
    }

}
