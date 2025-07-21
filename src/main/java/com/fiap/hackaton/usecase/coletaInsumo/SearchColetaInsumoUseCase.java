package com.fiap.hackaton.usecase.coletaInsumo;

import com.fiap.hackaton.entity.coletaInsumo.gateway.ColetaInsumoGateway;
import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.infrastructure.config.db.schema.ColetaInsumoSchema;

import java.util.List;

public class SearchColetaInsumoUseCase {

    private final ColetaInsumoGateway coletaInsumoGateway;

    public SearchColetaInsumoUseCase(ColetaInsumoGateway coletaInsumoGateway) {
        this.coletaInsumoGateway = coletaInsumoGateway;
    }

    public List<ColetaInsumo> execute() {
        return this.coletaInsumoGateway.findAll().stream().map(ColetaInsumoSchema::toColeta).toList();
    }

}
