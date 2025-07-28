package com.fiap.hackaton.usecase.core.colaborador.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface IColaboradorUpdateData {

    String nome();
    String cep();
    Integer numeroCasa();
    UUID hospitalId();
    String setor();
    OffsetDateTime ultimoDiaCadastro();

}
