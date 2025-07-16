package com.fiap.hackaton.usecase.insumo;

import com.fiap.hackaton.entity.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumo.model.Insumo;

import java.util.List;

public class SearchInsumoUseCase {

    private final InsumoGateway insumoGateway;

    public SearchInsumoUseCase(InsumoGateway insumoGateway) {
        this.insumoGateway = insumoGateway;
    }

    public List<Insumo> execute() {
        return this.insumoGateway.findAll();
    }

}
