package com.fiap.hackaton.usecase.hospital.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;

import java.util.List;
import java.util.UUID;

public interface IHospitalPublicData {

    UUID id();

    List<Colaborador> colaboradores();

    String cep();

    Integer numero();

}