package com.fiap.hackaton.usecase.equipamentoDominio.entregaEquipamento.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface IEntregaEquipamentoPublicData {

    UUID id();

    UUID equipamentoId();

    UUID colaboradorRecebedorId();

    OffsetDateTime dataHoraRecebimento();

    UUID hospitalId();

}
