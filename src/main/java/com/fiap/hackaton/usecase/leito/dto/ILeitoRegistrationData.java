package com.fiap.hackaton.usecase.leito.dto;

import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.paciente.model.Paciente;

public interface ILeitoRegistrationData {

    String identificacao();

    String pavilhao();

    String quarto();

    Hospital hospital();

    Paciente paciente();

}
