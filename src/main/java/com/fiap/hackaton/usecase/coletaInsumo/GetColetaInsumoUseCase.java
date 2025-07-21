package com.fiap.hackaton.usecase.coletaInsumo;

import com.fiap.hackaton.entity.coletaInsumo.exception.ColetaInsumoNotFoundException;
import com.fiap.hackaton.entity.coletaInsumo.gateway.ColetaInsumoGateway;
import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;

import java.util.UUID;

public class GetColetaInsumoUseCase {

    private final ColetaInsumoGateway coletaInsumoGateway;

    public GetColetaInsumoUseCase(ColetaInsumoGateway coletaInsumoGateway) {
        this.coletaInsumoGateway = coletaInsumoGateway;
    }

    public ColetaInsumo execute(UUID id) throws ColetaInsumoNotFoundException {
        return this.coletaInsumoGateway.findById(id).
                orElseThrow(ColetaInsumoNotFoundException::new).toColeta();
    }

}
