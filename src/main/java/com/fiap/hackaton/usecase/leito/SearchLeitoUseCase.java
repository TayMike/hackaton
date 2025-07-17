package com.fiap.hackaton.usecase.leito;

import com.fiap.hackaton.entity.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.leito.model.Leito;

import java.util.List;

public class SearchLeitoUseCase {

    private final LeitoGateway leitoGateway;

    public SearchLeitoUseCase(LeitoGateway leitoGateway) {
        this.leitoGateway = leitoGateway;
    }

    public List<Leito> execute() {
        return this.leitoGateway.findAll();
    }

}
