package com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface IEstoqueEquipamentoUpdateData {

    UUID colaboradorEntregadorId();

    OffsetDateTime dataHoraColeta();

    UUID colaboradorResponsavelId();

}
