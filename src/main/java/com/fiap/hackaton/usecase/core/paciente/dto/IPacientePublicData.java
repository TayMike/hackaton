package com.fiap.hackaton.usecase.core.paciente.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface IPacientePublicData {

    UUID id();

    String cpf();

    String nome();

    OffsetDateTime primeiroDiaCadastro();

    String cep();

    Integer numeroCasa();

}
