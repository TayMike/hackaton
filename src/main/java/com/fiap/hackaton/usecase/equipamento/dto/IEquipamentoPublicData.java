package com.fiap.hackaton.usecase.equipamento.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public interface IEquipamentoPublicData {

    UUID id();

    String nome();

    BigDecimal custo();

    OffsetDateTime tempoGarantia();

    OffsetDateTime proximaManutencaoPreventiva();

    String marca();

    UUID hospital();

}
