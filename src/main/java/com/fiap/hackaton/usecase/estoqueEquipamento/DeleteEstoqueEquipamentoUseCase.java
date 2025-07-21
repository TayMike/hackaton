package com.fiap.hackaton.usecase.estoqueEquipamento;

import com.fiap.hackaton.entity.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.entity.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;

import java.util.UUID;

public class DeleteEstoqueEquipamentoUseCase {

    private final EstoqueEquipamentoGateway estoqueGateway;

    public DeleteEstoqueEquipamentoUseCase(EstoqueEquipamentoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public EstoqueEquipamento execute(UUID id) throws EstoqueEquipamentoNotFoundException {
        EstoqueEquipamento estoqueEquipamento = estoqueGateway.findById(id)
                .orElseThrow(EstoqueEquipamentoNotFoundException::new).toEstoqueEquipamento();

        estoqueGateway.deleteById(id);

        return estoqueEquipamento;
    }

}
