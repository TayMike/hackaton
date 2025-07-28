package com.fiap.hackaton.usecase.core.leito;

import com.fiap.hackaton.entity.core.leito.exception.LeitoNotFoundException;
import com.fiap.hackaton.entity.core.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.core.leito.model.Leito;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class GetLeitoUseCase {

    private final LeitoGateway leitoGateway;

    public GetLeitoUseCase(LeitoGateway leitoGateway) {
        this.leitoGateway = leitoGateway;
    }

    @Transactional(readOnly = true)
    public Leito execute(UUID id) throws LeitoNotFoundException {
        return this.leitoGateway.findById(id).
                orElseThrow(() -> new LeitoNotFoundException("Leito not found: " + id));
    }

}
