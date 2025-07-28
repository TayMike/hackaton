package com.fiap.hackaton.usecase.insumoDominio.coletaInsumo;

import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.gateway.ColetaInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.model.ColetaInsumo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SearchColetaInsumoUseCase {

    private final ColetaInsumoGateway coletaInsumoGateway;

    public SearchColetaInsumoUseCase(ColetaInsumoGateway coletaInsumoGateway) {
        this.coletaInsumoGateway = coletaInsumoGateway;
    }

    @Transactional(readOnly = true)
    public List<ColetaInsumo> execute() {
        return this.coletaInsumoGateway.findAll();
    }

}
