package com.fiap.hackaton.usecase.estoqueInsumo.dto;

import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;

import java.util.List;

public interface IEstoqueInsumoRegistrationData {

    List<Insumo> itens();
    List<Long> quantidades();
    Hospital hospital();

}