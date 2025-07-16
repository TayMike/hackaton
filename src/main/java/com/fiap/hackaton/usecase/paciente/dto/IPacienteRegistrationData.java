package com.fiap.hackaton.usecase.paciente.dto;

import java.time.LocalDateTime;

public interface IPacienteRegistrationData {

    String cpf();
    String nome();
    LocalDateTime primeiroDiaCadastro();
    String cep();
    Integer numeroCasa();

}
