package com.fiap.hackaton.usecase.entregaInsumo;

import com.fiap.hackaton.entity.entregaInsumo.exception.EntregaInsumoNotFoundException;
import com.fiap.hackaton.entity.entregaInsumo.gateway.EntregaInsumoGateway;
import com.fiap.hackaton.entity.entregaInsumo.model.EntregaInsumo;

import java.util.UUID;

public class GetEntregaInsumoUseCase {

    private final EntregaInsumoGateway entregaInsumoGateway;

    public GetEntregaInsumoUseCase(EntregaInsumoGateway entregaInsumoGateway) {
        this.entregaInsumoGateway = entregaInsumoGateway;
    }

    public EntregaInsumo execute(UUID id) throws EntregaInsumoNotFoundException {
        return this.entregaInsumoGateway.findById(id).
                orElseThrow(EntregaInsumoNotFoundException::new);
    }

}
