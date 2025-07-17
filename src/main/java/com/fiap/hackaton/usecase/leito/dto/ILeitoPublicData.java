package com.fiap.hackaton.usecase.leito.dto;

import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.paciente.model.Paciente;

import java.util.UUID;

public interface ILeitoPublicData {

    UUID id();

    String identificacao();

    String pavilhao();

    String quarto();

    Hospital hospital();

    Paciente paciente();

}