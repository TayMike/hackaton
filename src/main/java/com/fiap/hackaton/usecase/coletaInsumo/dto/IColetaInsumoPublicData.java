package com.fiap.hackaton.usecase.coletaInsumo.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface IColetaInsumoPublicData {

    UUID id();

    List<UUID> insumos();

    List<Long> quantidades();

    UUID colaboradorEntregador();

    OffsetDateTime dataHoraColeta();

    UUID pacienteRecebedor();

    UUID hospital();

}
