package com.fiap.hackaton.usecase.core.hospital.dto;

import java.util.List;
import java.util.UUID;

public interface IHospitalPublicData {

    UUID id();

    String nome();

    String cnpj();

    List<UUID> colaboradoresIds();

    String cep();

    Integer numero();

    Integer quantidadeLeitoAtual();

    Integer quantidadeLeitoMaximo();

}