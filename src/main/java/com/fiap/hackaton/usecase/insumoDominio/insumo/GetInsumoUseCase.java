package com.fiap.hackaton.usecase.insumoDominio.insumo;

import com.fiap.hackaton.entity.insumoDominio.insumo.exception.InsumoNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class GetInsumoUseCase {

    private final InsumoGateway insumoGateway;

    public GetInsumoUseCase(InsumoGateway insumoGateway) {
        this.insumoGateway = insumoGateway;
    }

    @Transactional(readOnly = true)
    public Insumo execute(UUID id) throws InsumoNotFoundException {
        return this.insumoGateway.findById(id).
                orElseThrow(() -> new InsumoNotFoundException("Insumo not found: " + id));
    }

}
