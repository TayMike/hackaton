package com.fiap.hackaton.usecase.descarteEquipamento.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface IDescarteEquipamentoRegistrationData {

    List<UUID> equipamentos();

    List<Long> quantidade();

    UUID colaboradorResponsavel();

    OffsetDateTime dataHoraDescarte();

    UUID hospital();

}
