package com.fiap.hackaton.usecase.leito;

import com.fiap.hackaton.entity.leito.exception.LeitoNotFoundException;
import com.fiap.hackaton.entity.leito.gateway.LeitoGateway;

import java.util.UUID;

public class DeleteLeitoUseCase {

    private final LeitoGateway leitoGateway;

    public DeleteLeitoUseCase(LeitoGateway leitoGateway) {
        this.leitoGateway = leitoGateway;
    }

    public void execute(UUID id) throws LeitoNotFoundException {
        leitoGateway.findById(id)
                .orElseThrow(LeitoNotFoundException::new);

        leitoGateway.deleteById(id);
    }

}
