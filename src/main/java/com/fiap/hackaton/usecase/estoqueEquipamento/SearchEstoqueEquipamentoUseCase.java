package com.fiap.hackaton.usecase.estoqueEquipamento;

import com.fiap.hackaton.entity.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.infrastructure.config.db.schema.EstoqueEquipamentoSchema;

import java.util.List;

public class SearchEstoqueEquipamentoUseCase {

    private final EstoqueEquipamentoGateway estoqueGateway;

    public SearchEstoqueEquipamentoUseCase(EstoqueEquipamentoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public List<EstoqueEquipamento> execute() {
        return this.estoqueGateway.findAll().stream().map(EstoqueEquipamentoSchema::toEstoqueEquipamento).toList();
    }

}
