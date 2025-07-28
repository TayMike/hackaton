package com.fiap.hackaton.usecase.insumoDominio.insumo;

import com.fiap.hackaton.entity.insumoDominio.insumo.exception.InsumoNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.insumo.gateway.InsumoGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class DeleteInsumoUseCase {

    private final InsumoGateway insumoGateway;

    public DeleteInsumoUseCase(InsumoGateway insumoGateway) {
        this.insumoGateway = insumoGateway;
    }

    @Transactional
    public void execute(UUID id) throws InsumoNotFoundException {
        insumoGateway.findById(id)
                .orElseThrow(() -> new InsumoNotFoundException("Insumo not found: " + id));

        insumoGateway.deleteById(id);
    }

}
