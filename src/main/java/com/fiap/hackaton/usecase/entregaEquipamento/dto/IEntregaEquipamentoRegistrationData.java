package com.fiap.hackaton.usecase.entregaEquipamento.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface IEntregaEquipamentoRegistrationData {

    List<UUID> equipamentos();

    List<Long> quantidade();

    UUID colaboradorRecebedor();

    OffsetDateTime dataHoraRecebimento();

    UUID hospital();

}
