package com.fiap.hackaton.usecase.insumo;

import com.fiap.hackaton.entity.insumo.exception.InsumoNotFoundException;
import com.fiap.hackaton.entity.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumo.model.Insumo;

import java.util.UUID;

public class GetInsumoUseCase {

    private final InsumoGateway insumoGateway;

    public GetInsumoUseCase(InsumoGateway insumoGateway) {
        this.insumoGateway = insumoGateway;
    }

    public Insumo execute(UUID id) throws InsumoNotFoundException {
        return this.insumoGateway.findById(id).
                orElseThrow(InsumoNotFoundException::new).toInsumo();
    }

}
