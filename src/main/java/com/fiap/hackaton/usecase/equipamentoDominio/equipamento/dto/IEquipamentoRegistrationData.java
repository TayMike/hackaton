package com.fiap.hackaton.usecase.equipamentoDominio.equipamento.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface IEquipamentoRegistrationData {

    String nome();

    BigDecimal custo();

    OffsetDateTime tempoGarantia();

    OffsetDateTime proximaManutencaoPreventiva();

    String numeroSerie();

    String marca();

    OffsetDateTime descarte();

}
