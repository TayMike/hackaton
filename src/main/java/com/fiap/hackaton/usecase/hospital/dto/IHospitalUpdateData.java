package com.fiap.hackaton.usecase.hospital.dto;

import java.util.List;
import java.util.UUID;

public interface IHospitalUpdateData {

    List<UUID> colaboradores();

    String cep();

    Integer numero();

    Integer quantidadeLeitoAtual();

    Integer quantidadeLeitoMaximo();
}