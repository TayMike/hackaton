package com.fiap.hackaton.usecase.estoqueEquipamento;

import com.fiap.hackaton.entity.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.usecase.estoqueEquipamento.dto.IEstoqueEquipamentoRegistrationData;

public class CreateEstoqueEquipamentoUseCase {

    private final EstoqueEquipamentoGateway estoqueGateway;

    public CreateEstoqueEquipamentoUseCase(EstoqueEquipamentoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public EstoqueEquipamento execute(IEstoqueEquipamentoRegistrationData dados) {

        EstoqueEquipamento estoqueEquipamento = new EstoqueEquipamento(dados.itens(), dados.quantidades(), dados.hospital());

        return this.estoqueGateway.save(estoqueEquipamento);
    }


}
