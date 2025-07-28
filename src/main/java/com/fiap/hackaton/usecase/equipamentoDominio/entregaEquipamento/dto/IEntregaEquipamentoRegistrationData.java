package com.fiap.hackaton.usecase.equipamentoDominio.entregaEquipamento.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface IEntregaEquipamentoRegistrationData {

    UUID equipamentoId();

    UUID colaboradorRecebedorId();

    OffsetDateTime dataHoraRecebimento();

    UUID hospitalId();

}
