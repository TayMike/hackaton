package com.fiap.hackaton.usecase.hospital.dto;

import java.util.List;
import java.util.UUID;

public interface IHospitalPublicData {

    UUID id();

    String nome();

    String cnpj();

    List<UUID> colaboradores();

    String cep();

    Integer numero();

    Integer quantidadeLeitoAtual();

    Integer quantidadeLeitoMaximo();

}