package com.fiap.hackaton.usecase.insumoDominio.insumo;

import com.fiap.hackaton.entity.insumoDominio.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SearchInsumoUseCase {

    private final InsumoGateway insumoGateway;

    public SearchInsumoUseCase(InsumoGateway insumoGateway) {
        this.insumoGateway = insumoGateway;
    }

    @Transactional(readOnly = true)
    public List<Insumo> execute() {
        return this.insumoGateway.findAll();
    }

}
