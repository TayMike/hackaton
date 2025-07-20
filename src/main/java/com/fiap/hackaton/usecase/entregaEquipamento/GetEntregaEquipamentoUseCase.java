package com.fiap.hackaton.usecase.entregaEquipamento;

import com.fiap.hackaton.entity.entregaEquipamento.exception.EntregaEquipamentoNotFoundException;
import com.fiap.hackaton.entity.entregaEquipamento.gateway.EntregaEquipamentoGateway;
import com.fiap.hackaton.entity.entregaEquipamento.model.EntregaEquipamento;

import java.util.UUID;

public class GetEntregaEquipamentoUseCase {

    private final EntregaEquipamentoGateway entregaEquipamentoGateway;

    public GetEntregaEquipamentoUseCase(EntregaEquipamentoGateway entregaEquipamentoGateway) {
        this.entregaEquipamentoGateway = entregaEquipamentoGateway;
    }

    public EntregaEquipamento execute(UUID id) throws EntregaEquipamentoNotFoundException {
        return this.entregaEquipamentoGateway.findById(id).
                orElseThrow(EntregaEquipamentoNotFoundException::new);
    }

}
