package com.fiap.hackaton.infrastructure.estoqueEquipamento.dto;

import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.usecase.estoqueEquipamento.dto.IEstoqueEquipamentoRegistrationData;

import java.util.List;
import java.util.UUID;

public record EstoqueEquipamentoRegistrationData (
        List<UUID> itens,
        List<Long> quantidades,
        UUID hospital
) implements IEstoqueEquipamentoRegistrationData {

    public EstoqueEquipamentoRegistrationData(EstoqueEquipamento estoqueEquipamento) {
        this(
                estoqueEquipamento.getItens().stream().map(Equipamento::getId).toList(),
                estoqueEquipamento.getQuantidades(),
                estoqueEquipamento.getHospital().getId()
        );
    }
}
