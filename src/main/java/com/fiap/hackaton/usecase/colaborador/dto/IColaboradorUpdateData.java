package com.fiap.hackaton.usecase.colaborador.dto;

import java.util.UUID;

public interface IColaboradorUpdateData {

    String nome();

    String cep();

    Integer numeroCasa();

    UUID hospital();

    String setor();

    Boolean ativo();

}
