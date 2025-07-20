package com.fiap.hackaton.usecase.estoqueEquipamento;

import com.fiap.hackaton.entity.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.entity.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.usecase.estoqueEquipamento.dto.IEstoqueEquipamentoUpdateData;

import java.util.UUID;

public class UpdateEstoqueEquipamentoUseCase {

    private final EstoqueEquipamentoGateway estoqueGateway;

    public UpdateEstoqueEquipamentoUseCase(EstoqueEquipamentoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public EstoqueEquipamento execute(UUID id, IEstoqueEquipamentoUpdateData dados) throws EstoqueEquipamentoNotFoundException {
        EstoqueEquipamento estoqueEquipamento = this.estoqueGateway.findById(id)
                .orElseThrow(EstoqueEquipamentoNotFoundException::new);

        if (dados.itens() != null && !dados.itens().isEmpty())
            estoqueEquipamento.setItens(dados.itens());

        if (dados.quantidades() != null && !dados.quantidades().isEmpty())
            estoqueEquipamento.setQuantidades(dados.quantidades());

        if (dados.hospital() != null)
            estoqueEquipamento.setHospital(dados.hospital());

        return this.estoqueGateway.update(estoqueEquipamento);
    }

}
