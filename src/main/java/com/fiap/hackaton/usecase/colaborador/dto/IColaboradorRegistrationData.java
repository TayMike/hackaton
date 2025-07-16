package com.fiap.hackaton.usecase.colaborador.dto;

import com.fiap.hackaton.entity.hospital.model.Hospital;

import java.time.LocalDateTime;

public interface IColaboradorRegistrationData {

    String cpf();
    String nome();
    String matricula();
    LocalDateTime primeiroDiaCadastro();
    String cep();
    Integer numeroCasa();
    Hospital hospital();
    String setor();
    Boolean ativo();

}
