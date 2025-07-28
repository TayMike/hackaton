package com.fiap.hackaton.usecase.core.leito;

import com.fiap.hackaton.entity.core.leito.exception.LeitoNotFoundException;
import com.fiap.hackaton.entity.core.leito.gateway.LeitoGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class DeleteLeitoUseCase {

    private final LeitoGateway leitoGateway;

    public DeleteLeitoUseCase(LeitoGateway leitoGateway) {
        this.leitoGateway = leitoGateway;
    }

    @Transactional
    public void execute(UUID id) throws LeitoNotFoundException {
        leitoGateway.findById(id).orElseThrow(() -> new LeitoNotFoundException("Leito not found: " + id));

        leitoGateway.deleteById(id);
    }

}
