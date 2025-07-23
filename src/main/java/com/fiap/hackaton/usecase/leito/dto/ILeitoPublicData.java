package com.fiap.hackaton.usecase.leito.dto;

import java.util.UUID;

public interface ILeitoPublicData {

    UUID id();

    String identificacao();

    String pavilhao();

    String quarto();

    UUID hospital();

    UUID paciente();

}