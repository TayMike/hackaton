package com.fiap.hackaton.usecase.estoqueInsumo;

import com.fiap.hackaton.entity.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.entity.insumo.model.Insumo;

import java.util.List;

public class SearchEstoqueUseCase {

    private final EstoqueInsumoGateway estoqueGateway;

    public SearchEstoqueUseCase(EstoqueInsumoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public List<EstoqueInsumo> execute() {
        return this.estoqueGateway.findAll();
    }

}
