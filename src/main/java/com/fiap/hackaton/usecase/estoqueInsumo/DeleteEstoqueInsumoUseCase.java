package com.fiap.hackaton.usecase.estoqueInsumo;

import com.fiap.hackaton.entity.estoqueInsumo.exception.EstoqueInsumoNotFoundException;
import com.fiap.hackaton.entity.estoqueInsumo.gateway.EstoqueInsumoGateway;

import java.util.UUID;

public class DeleteEstoqueInsumoUseCase {

    private final EstoqueInsumoGateway estoqueGateway;

    public DeleteEstoqueInsumoUseCase(EstoqueInsumoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public void execute(UUID id) throws EstoqueInsumoNotFoundException {
        estoqueGateway.findById(id)
                .orElseThrow(EstoqueInsumoNotFoundException::new);

        estoqueGateway.deleteById(id);
    }

}
