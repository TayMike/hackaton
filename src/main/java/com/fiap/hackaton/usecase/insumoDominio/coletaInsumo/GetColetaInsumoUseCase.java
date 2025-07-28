package com.fiap.hackaton.usecase.insumoDominio.coletaInsumo;

import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.exception.ColetaInsumoNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.gateway.ColetaInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.model.ColetaInsumo;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class GetColetaInsumoUseCase {

    private final ColetaInsumoGateway coletaInsumoGateway;

    public GetColetaInsumoUseCase(ColetaInsumoGateway coletaInsumoGateway) {
        this.coletaInsumoGateway = coletaInsumoGateway;
    }

    @Transactional(readOnly = true)
    public ColetaInsumo execute(UUID id) throws ColetaInsumoNotFoundException {
        return this.coletaInsumoGateway.findById(id).
                orElseThrow(() -> new ColetaInsumoNotFoundException("Coleta not found: " + id));
    }

}
