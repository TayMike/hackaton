package com.fiap.hackaton.usecase.colaborador.dto;

import com.fiap.hackaton.entity.hospital.model.Hospital;

public interface IColaboradorUpdateData {

    String nome();

    String cep();

    Integer numeroCasa();

    Hospital hospital();

    String setor();

    Boolean ativo();

}
