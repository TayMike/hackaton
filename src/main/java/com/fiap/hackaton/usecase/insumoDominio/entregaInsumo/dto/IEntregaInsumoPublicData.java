package com.fiap.hackaton.usecase.insumoDominio.entregaInsumo.dto;

import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface IEntregaInsumoPublicData {

    UUID id();
    List<QuantidadeInsumo> insumos();
    UUID colaboradorRecebedorId();
    OffsetDateTime dataHoraRecebimento();
    UUID hospitalId();

}
