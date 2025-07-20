package com.fiap.hackaton.usecase.estoqueEquipamento.dto;

import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;

import java.util.List;

public interface IEstoqueEquipamentoRegistrationData {

    List<Equipamento> itens();

    List<Long> quantidades();

    Hospital hospital();

}
