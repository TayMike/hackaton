package com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface IEstoqueEquipamentoPublicData {

    UUID id();

    UUID equipamentoId();

    UUID hospitalId();

    UUID colaboradorEntregadorId();

    OffsetDateTime dataHoraColeta();

    UUID colaboradorResponsavelId();

}
