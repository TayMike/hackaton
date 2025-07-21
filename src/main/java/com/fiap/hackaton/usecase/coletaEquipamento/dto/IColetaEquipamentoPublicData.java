package com.fiap.hackaton.usecase.coletaEquipamento.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface IColetaEquipamentoPublicData {

    UUID id();

    List<UUID> equipamentos();

    List<Long> quantidades();

    UUID colaboradorEntregador();

    OffsetDateTime dataHoraColeta();

    UUID colaboradorResponsavel();

    UUID hospital();

}
