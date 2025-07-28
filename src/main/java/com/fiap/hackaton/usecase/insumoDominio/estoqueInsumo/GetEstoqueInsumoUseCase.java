package com.fiap.hackaton.usecase.insumoDominio.estoqueInsumo;

import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.exception.EstoqueInsumoNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.model.EstoqueInsumo;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class GetEstoqueInsumoUseCase {

    private final EstoqueInsumoGateway estoqueGateway;

    public GetEstoqueInsumoUseCase(EstoqueInsumoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    @Transactional(readOnly = true)
    public EstoqueInsumo execute(UUID id) throws EstoqueInsumoNotFoundException {
        return this.estoqueGateway.findByHospitalId(id).
                orElseThrow(() -> new EstoqueInsumoNotFoundException("Estoque not found: " + id));
    }

}
