package com.fiap.hackaton.infrastructure.estoqueEquipamento.dto;

import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.usecase.estoqueEquipamento.dto.IEstoqueEquipamentoRegistrationData;

import java.util.List;

public record EstoqueEquipamentoRegistrationData (
        List<Equipamento> itens,
        List<Long> quantidades,
        Hospital hospital
) implements IEstoqueEquipamentoRegistrationData {

    public EstoqueEquipamentoRegistrationData(EstoqueEquipamento estoqueEquipamento) {
        this(
                estoqueEquipamento.getItens(),
                estoqueEquipamento.getQuantidades(),
                estoqueEquipamento.getHospital()
        );
    }
}
