package com.fiap.hackaton.usecase.colaborador.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface IColaboradorRegistrationData {

    String cpf();
    String nome();
    String matricula();
    OffsetDateTime primeiroDiaCadastro();
    String cep();
    Integer numeroCasa();
    UUID hospital();
    String setor();
    Boolean ativo();

}
