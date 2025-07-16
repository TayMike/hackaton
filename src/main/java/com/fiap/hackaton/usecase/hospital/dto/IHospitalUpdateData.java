package com.fiap.hackaton.usecase.hospital.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;

import java.util.List;

public interface IHospitalUpdateData {

    List<Colaborador> colaboradores();
    String cep();
    Integer numero();

}
