package com.fiap.hackaton.infrastructure.estoqueEquipamento.dto;

import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.usecase.estoqueEquipamento.dto.IEstoqueEquipamentoUpdateData;

import java.util.List;

public record EstoqueEquipamentoUpdateData(
        List<Equipamento> itens,
        List<Long> quantidades,
        Hospital hospital
) implements IEstoqueEquipamentoUpdateData {

    public EstoqueEquipamentoUpdateData(EstoqueEquipamento estoqueEquipamento) {
        this(
                estoqueEquipamento.getItens(),
                estoqueEquipamento.getQuantidades(),
                estoqueEquipamento.getHospital()
        );
    }
}
