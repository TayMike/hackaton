package com.fiap.hackaton.usecase.leito;

import com.fiap.hackaton.entity.leito.exception.LeitoNotFoundException;
import com.fiap.hackaton.entity.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.leito.model.Leito;

import java.util.UUID;

public class GetLeitoUseCase {

    private final LeitoGateway leitoGateway;

    public GetLeitoUseCase(LeitoGateway leitoGateway) {
        this.leitoGateway = leitoGateway;
    }

    public Leito execute(UUID id) throws LeitoNotFoundException {
        return this.leitoGateway.findById(id).
                orElseThrow(LeitoNotFoundException::new);
    }

}
