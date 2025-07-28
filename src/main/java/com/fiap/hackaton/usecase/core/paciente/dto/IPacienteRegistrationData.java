package com.fiap.hackaton.usecase.core.paciente.dto;

import java.time.OffsetDateTime;

public interface IPacienteRegistrationData {

    String cpf();
    String nome();
    OffsetDateTime primeiroDiaCadastro();
    String cep();
    Integer numeroCasa();

}