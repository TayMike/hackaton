package com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento;

import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.model.EstoqueEquipamento;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SearchEstoqueEquipamentoUseCase {

    private final EstoqueEquipamentoGateway estoqueGateway;

    public SearchEstoqueEquipamentoUseCase(EstoqueEquipamentoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    @Transactional(readOnly = true)
    public List<EstoqueEquipamento> execute() {
        return this.estoqueGateway.findAll();
    }

}
