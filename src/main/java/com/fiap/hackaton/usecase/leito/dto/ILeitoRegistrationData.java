package com.fiap.hackaton.usecase.leito.dto;

import java.util.UUID;

public interface ILeitoRegistrationData {

    String identificacao();

    String pavilhao();

    String quarto();

    UUID hospital();

    UUID paciente();

}
