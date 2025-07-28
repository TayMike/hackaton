package com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.model;

import com.fiap.hackaton.entity.auxiliar.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class EntregaEquipamento extends AbstractEntity {

    @NonNull
    private UUID equipamentoId;
    @NonNull
    private UUID colaboradorRecebedorId;
    @NonNull
    private OffsetDateTime dataHoraRecebimento;
    @NonNull
    private UUID hospitalId;

    public EntregaEquipamento(@NonNull UUID equipamentoId, @NonNull UUID colaboradorRecebedorId, @NonNull OffsetDateTime dataHoraRecebimento, @NonNull UUID hospitalId) {
        this.equipamentoId = equipamentoId;
        this.colaboradorRecebedorId = colaboradorRecebedorId;
        this.dataHoraRecebimento = dataHoraRecebimento;
        this.hospitalId = hospitalId;
    }

}
