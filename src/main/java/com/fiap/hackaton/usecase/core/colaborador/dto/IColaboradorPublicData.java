package com.fiap.hackaton.usecase.core.colaborador.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface IColaboradorPublicData {

    UUID id();
    String cpf();
    String nome();
    String matricula();
    OffsetDateTime primeiroDiaCadastro();
    OffsetDateTime ultimoDiaCadastro();
    String cep();
    Integer numeroCasa();
    UUID hospitalId();
    String setor();

}
