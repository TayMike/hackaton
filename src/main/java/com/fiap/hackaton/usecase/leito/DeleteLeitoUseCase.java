package com.fiap.hackaton.usecase.leito;

import com.fiap.hackaton.entity.leito.exception.LeitoNotFoundException;
import com.fiap.hackaton.entity.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.leito.model.Leito;

import java.util.UUID;

public class DeleteLeitoUseCase {

    private final LeitoGateway leitoGateway;

    public DeleteLeitoUseCase(LeitoGateway leitoGateway) {
        this.leitoGateway = leitoGateway;
    }

    public Leito execute(UUID id) throws LeitoNotFoundException {
        Leito leito = leitoGateway.findById(id)
                .orElseThrow(LeitoNotFoundException::new);

        leitoGateway.deleteById(id);

        return leito;
    }

}
