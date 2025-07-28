package com.fiap.hackaton.usecase.insumoDominio.estoqueInsumo;

import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.model.EstoqueInsumo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SearchEstoqueInsumoUseCase {

    private final EstoqueInsumoGateway estoqueGateway;

    public SearchEstoqueInsumoUseCase(EstoqueInsumoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    @Transactional(readOnly = true)
    public List<EstoqueInsumo> execute() {
        return this.estoqueGateway.findAll();
    }

}
