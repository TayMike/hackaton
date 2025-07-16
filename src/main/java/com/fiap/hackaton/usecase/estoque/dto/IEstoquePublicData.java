package com.fiap.hackaton.usecase.estoque.dto;

import com.fiap.hackaton.entity.hospital.model.Hospital;

import java.util.List;
import java.util.UUID;

public interface IEstoquePublicData<T> {

    UUID id();
    List<T> itens();
    List<Long> quantidades();
    Hospital hospital();

}
