package com.fiap.hackaton.usecase.insumoDominio.entregaInsumo;

import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.gateway.EntregaInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.model.EntregaInsumo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SearchEntregaInsumoUseCase {

    private final EntregaInsumoGateway entregaInsumoGateway;

    public SearchEntregaInsumoUseCase(EntregaInsumoGateway entregaInsumoGateway) {
        this.entregaInsumoGateway = entregaInsumoGateway;
    }

    @Transactional(readOnly = true)
    public List<EntregaInsumo> execute() {
        return this.entregaInsumoGateway.findAll();
    }

}
