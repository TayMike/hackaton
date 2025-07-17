package com.fiap.hackaton.usecase.entregaInsumo;

import com.fiap.hackaton.entity.entregaInsumo.gateway.EntregaInsumoGateway;
import com.fiap.hackaton.entity.entregaInsumo.model.EntregaInsumo;

import java.util.List;

public class SearchEntregaInsumoUseCase {

    private final EntregaInsumoGateway entregaInsumoGateway;

    public SearchEntregaInsumoUseCase(EntregaInsumoGateway entregaInsumoGateway) {
        this.entregaInsumoGateway = entregaInsumoGateway;
    }

    public List<EntregaInsumo> execute() {
        return this.entregaInsumoGateway.findAll();
    }

}
