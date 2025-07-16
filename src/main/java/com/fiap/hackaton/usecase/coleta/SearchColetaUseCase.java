package com.fiap.hackaton.usecase.coleta;

import com.fiap.hackaton.entity.coleta.gateway.ColetaGateway;
import com.fiap.hackaton.entity.coleta.model.Coleta;

import java.util.List;

public class SearchColetaUseCase {

    private final ColetaGateway coletaGateway;

    public SearchColetaUseCase(ColetaGateway coletaGateway) {
        this.coletaGateway = coletaGateway;
    }

    public List<Coleta> execute() {
        return this.coletaGateway.findAll();
    }

}
