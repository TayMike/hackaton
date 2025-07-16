package com.fiap.hackaton.usecase.entrega;

import com.fiap.hackaton.entity.entrega.gateway.EntregaGateway;
import com.fiap.hackaton.entity.entrega.model.Entrega;

import java.util.List;

public class SearchEntregaUseCase {

    private final EntregaGateway entregaGateway;

    public SearchEntregaUseCase(EntregaGateway entregaGateway) {
        this.entregaGateway = entregaGateway;
    }

    public List<Entrega> execute() {
        return this.entregaGateway.findAll();
    }

}
