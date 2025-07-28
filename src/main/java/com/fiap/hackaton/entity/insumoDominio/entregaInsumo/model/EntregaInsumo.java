package com.fiap.hackaton.entity.insumoDominio.entregaInsumo.model;

import com.fiap.hackaton.entity.auxiliar.AbstractEntity;
import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class EntregaInsumo extends AbstractEntity {

    @NonNull
    private List<QuantidadeInsumo> insumos;
    @NonNull
    private UUID colaboradorRecebedorId;
    @NonNull
    private OffsetDateTime dataHoraRecebimento;
    @NonNull
    private UUID hospitalId;

    public EntregaInsumo(@NonNull List<QuantidadeInsumo> insumos, @NonNull UUID colaboradorRecebedorId, @NonNull OffsetDateTime dataHoraRecebimento, @NonNull UUID hospitalId) {
        this.insumos = insumos;
        this.colaboradorRecebedorId = colaboradorRecebedorId;
        this.dataHoraRecebimento = dataHoraRecebimento;
        this.hospitalId = hospitalId;
    }

}