package com.fiap.hackaton.usecase.estoqueEquipamento;

import com.fiap.hackaton.entity.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.entity.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;

import java.util.UUID;

public class DeleteEstoqueEquipamentoUseCase {

    private final EstoqueEquipamentoGateway estoqueGateway;

    public DeleteEstoqueEquipamentoUseCase(EstoqueEquipamentoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public void execute(UUID id) throws EstoqueEquipamentoNotFoundException {
        estoqueGateway.findById(id)
                .orElseThrow(EstoqueEquipamentoNotFoundException::new);

        estoqueGateway.deleteById(id);
    }

}
