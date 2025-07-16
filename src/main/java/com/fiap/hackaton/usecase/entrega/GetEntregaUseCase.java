package com.fiap.hackaton.usecase.entrega;

import com.fiap.hackaton.entity.entrega.exception.EntregaNotFoundException;
import com.fiap.hackaton.entity.entrega.gateway.EntregaGateway;
import com.fiap.hackaton.entity.entrega.model.Entrega;

import java.util.UUID;

public class GetEntregaUseCase {

    private final EntregaGateway entregaGateway;

    public GetEntregaUseCase(EntregaGateway entregaGateway) {
        this.entregaGateway = entregaGateway;
    }

    public Entrega execute(UUID id) throws EntregaNotFoundException {
        return this.entregaGateway.findById(id).
                orElseThrow(EntregaNotFoundException::new);
    }

}
