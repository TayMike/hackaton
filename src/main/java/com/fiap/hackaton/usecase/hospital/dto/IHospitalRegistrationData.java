package com.fiap.hackaton.usecase.hospital.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;

import java.util.List;

public interface IHospitalRegistrationData {

    List<Colaborador> colaboradores();
    String cep();
    Integer numero();
    Integer quantidadeLeitoAtual();
    Integer quantidadeLeitoMaximo();

}