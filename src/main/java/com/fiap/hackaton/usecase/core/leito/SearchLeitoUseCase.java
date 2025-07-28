package com.fiap.hackaton.usecase.core.leito;

import com.fiap.hackaton.entity.core.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.core.leito.model.Leito;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SearchLeitoUseCase {

    private final LeitoGateway leitoGateway;

    public SearchLeitoUseCase(LeitoGateway leitoGateway) {
        this.leitoGateway = leitoGateway;
    }

    @Transactional(readOnly = true)
    public List<Leito> execute() {
        return this.leitoGateway.findAll();
    }

}
