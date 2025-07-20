package com.fiap.hackaton.infrastructure.estoqueEquipamento.dto;

import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.usecase.estoqueEquipamento.dto.IEstoqueEquipamentoPublicData;

import java.util.List;
import java.util.UUID;

public record EstoqueEquipamentoPublicData(
        UUID id,
        List<Equipamento> itens,
        List<Long> quantidades,
        Hospital hospital
) implements IEstoqueEquipamentoPublicData {

    public EstoqueEquipamentoPublicData(EstoqueEquipamento estoqueEquipamento) {
        this(
                estoqueEquipamento.getId(),
                estoqueEquipamento.getItens(),
                estoqueEquipamento.getQuantidades(),
                estoqueEquipamento.getHospital()
        );
    }
}
