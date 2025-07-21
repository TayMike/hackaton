package com.fiap.hackaton.usecase.estoqueInsumo;

import com.fiap.hackaton.entity.estoqueInsumo.exception.EstoqueInsumoNotFoundException;
import com.fiap.hackaton.entity.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;

import java.util.UUID;

public class DeleteEstoqueInsumoUseCase {

    private final EstoqueInsumoGateway estoqueGateway;

    public DeleteEstoqueInsumoUseCase(EstoqueInsumoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public EstoqueInsumo execute(UUID id) throws EstoqueInsumoNotFoundException {
        EstoqueInsumo estoqueInsumo = estoqueGateway.findById(id)
                .orElseThrow(EstoqueInsumoNotFoundException::new).toEstoqueInsumo();

        estoqueGateway.deleteById(id);

        return estoqueInsumo;
    }

}
