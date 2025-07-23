package com.fiap.hackaton.infrastructure.estoqueEquipamento.dto;

import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.usecase.estoqueEquipamento.dto.IEstoqueEquipamentoPublicData;

import java.util.List;
import java.util.UUID;

public record EstoqueEquipamentoPublicData(
        UUID id,
        List<UUID> itens,
        List<Long> quantidades,
        UUID hospital
) implements IEstoqueEquipamentoPublicData {

    public EstoqueEquipamentoPublicData(EstoqueEquipamento estoqueEquipamento) {
        this(
                estoqueEquipamento.getId(),
                estoqueEquipamento.getItens().stream().map(Equipamento::getId).toList(),
                estoqueEquipamento.getQuantidades(),
                estoqueEquipamento.getHospital().getId()
        );
    }
}
