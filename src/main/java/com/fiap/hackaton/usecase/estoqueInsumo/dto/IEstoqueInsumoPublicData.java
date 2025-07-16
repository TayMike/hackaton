package com.fiap.hackaton.usecase.estoqueInsumo.dto;

import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;

import java.util.List;
import java.util.UUID;

public interface IEstoqueInsumoPublicData {

    UUID id();
    List<Insumo> itens();
    List<Long> quantidades();
    Hospital hospital();

}
