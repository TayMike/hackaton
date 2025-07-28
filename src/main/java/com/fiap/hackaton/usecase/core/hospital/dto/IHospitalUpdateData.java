package com.fiap.hackaton.usecase.core.hospital.dto;

import java.util.List;
import java.util.UUID;

public interface IHospitalUpdateData {

    List<UUID> colaboradoresIds();

    String cep();

    Integer numero();

    Integer quantidadeLeitoAtual();

    Integer quantidadeLeitoMaximo();
}