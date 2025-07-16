package com.fiap.hackaton.usecase.coleta;

import com.fiap.hackaton.entity.coleta.exception.ColetaNotFoundException;
import com.fiap.hackaton.entity.coleta.gateway.ColetaGateway;
import com.fiap.hackaton.entity.coleta.model.Coleta;

import java.util.UUID;

public class GetColetaUseCase {

    private final ColetaGateway coletaGateway;

    public GetColetaUseCase(ColetaGateway coletaGateway) {
        this.coletaGateway = coletaGateway;
    }

    public Coleta execute(UUID id) throws ColetaNotFoundException {
        return this.coletaGateway.findById(id).
                orElseThrow(ColetaNotFoundException::new);
    }

}
