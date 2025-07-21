package com.fiap.hackaton.usecase.entregaInsumo.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface IEntregaInsumoRegistrationData {

    List<UUID> insumo();

    List<Long> quantidade();

    UUID colaboradorRecebedor();

    OffsetDateTime dataHoraRecebimento();

    UUID hospital();

}
