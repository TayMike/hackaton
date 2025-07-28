package com.fiap.hackaton.usecase.core.leito.dto;

import java.util.UUID;

public interface ILeitoPublicData {

    UUID id();

    String identificacao();

    String pavilhao();

    String quarto();

    UUID hospitalId();

    UUID pacienteId();

}