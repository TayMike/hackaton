package com.fiap.hackaton.infrastructure.estoqueEquipamento.dto;

import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.usecase.estoqueEquipamento.dto.IEstoqueEquipamentoUpdateData;

import java.util.List;
import java.util.UUID;

public record EstoqueEquipamentoUpdateData(
        List<UUID> itens,
        List<Long> quantidades,
        UUID hospital
) implements IEstoqueEquipamentoUpdateData {

    public EstoqueEquipamentoUpdateData(EstoqueEquipamento estoqueEquipamento) {
        this(
                estoqueEquipamento.getItens().stream().map(Equipamento::getId).toList(),
                estoqueEquipamento.getQuantidades(),
                estoqueEquipamento.getHospital().getId()
        );
    }
}
