package com.fiap.hackaton.usecase.estoqueInsumo;

import com.fiap.hackaton.entity.estoqueInsumo.exception.EstoqueInsumoNotFoundException;
import com.fiap.hackaton.entity.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;

import java.util.UUID;

public class GetEstoqueInsumoUseCase {

    private final EstoqueInsumoGateway estoqueGateway;

    public GetEstoqueInsumoUseCase(EstoqueInsumoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public EstoqueInsumo execute(UUID id) throws EstoqueInsumoNotFoundException {
        return this.estoqueGateway.findById(id).
                orElseThrow(EstoqueInsumoNotFoundException::new);
    }

}
