package com.fiap.hackaton.usecase.insumoDominio.entregaInsumo;

import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.exception.EntregaInsumoNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.gateway.EntregaInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.model.EntregaInsumo;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class GetEntregaInsumoUseCase {

    private final EntregaInsumoGateway entregaInsumoGateway;

    public GetEntregaInsumoUseCase(EntregaInsumoGateway entregaInsumoGateway) {
        this.entregaInsumoGateway = entregaInsumoGateway;
    }

    @Transactional(readOnly = true)
    public EntregaInsumo execute(UUID id) throws EntregaInsumoNotFoundException {
        return this.entregaInsumoGateway.findById(id).
                orElseThrow(() -> new EntregaInsumoNotFoundException("Entrega not found: " + id));
    }

}
