package com.fiap.hackaton.usecase.paciente.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IPacientePublicData {

    UUID id();

    String cpf();

    String nome();

    LocalDateTime primeiroDiaCadastro();

    String cep();

    Integer numeroCasa();

}
