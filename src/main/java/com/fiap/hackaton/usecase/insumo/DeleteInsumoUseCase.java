package com.fiap.hackaton.usecase.insumo;

import com.fiap.hackaton.entity.insumo.exception.InsumoNotFoundException;
import com.fiap.hackaton.entity.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumo.model.Insumo;

import java.util.UUID;

public class DeleteInsumoUseCase {

    private final InsumoGateway insumoGateway;

    public DeleteInsumoUseCase(InsumoGateway insumoGateway) {
        this.insumoGateway = insumoGateway;
    }

    public Insumo execute(UUID id) throws InsumoNotFoundException {
        Insumo insumo = insumoGateway.findById(id)
                .orElseThrow(InsumoNotFoundException::new);

        insumoGateway.deleteById(id);

        return insumo;
    }

}
