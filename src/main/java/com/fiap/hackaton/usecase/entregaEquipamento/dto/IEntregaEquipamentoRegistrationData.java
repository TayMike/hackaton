package com.fiap.hackaton.usecase.entregaEquipamento.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IEntregaEquipamentoRegistrationData {

    List<UUID> equipamentos();

    List<Long> quantidade();

    UUID colaboradorRecebedor();

    LocalDateTime dataHoraRecebimento();

    UUID hospital();

}
