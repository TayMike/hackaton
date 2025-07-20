package com.fiap.hackaton.usecase.estoqueEquipamento.dto;

import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import java.util.List;
import java.util.UUID;

public interface IEstoqueEquipamentoPublicData {

    UUID id();

    List<Equipamento> itens();

    List<Long> quantidades();

    Hospital hospital();

}
