package com.fiap.hackaton.usecase.estoque.dto;

import com.fiap.hackaton.entity.hospital.model.Hospital;

import java.util.List;

public interface IEstoqueUpdateData<T> {

    List<T> itens();
    List<Long> quantidades();
    Hospital hospital();

}
