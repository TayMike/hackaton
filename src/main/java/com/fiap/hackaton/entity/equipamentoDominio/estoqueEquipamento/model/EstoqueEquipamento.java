package com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.model;

import com.fiap.hackaton.entity.auxiliar.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class EstoqueEquipamento extends AbstractEntity implements Serializable {

    @NonNull
    private UUID equipamentoId;
    @NonNull
    private UUID hospitalId;
    private UUID colaboradorEntregadorId;
    private OffsetDateTime dataHoraColeta;
    private UUID colaboradorResponsavelId;

    public EstoqueEquipamento(@NonNull UUID equipamentoId, @NonNull UUID hospitalId, UUID colaboradorEntregadorId, OffsetDateTime dataHoraColeta, UUID colaboradorResponsavelId) {
        this.equipamentoId = equipamentoId;
        this.hospitalId = hospitalId;
        this.colaboradorEntregadorId = colaboradorEntregadorId;
        this.dataHoraColeta = dataHoraColeta;
        this.colaboradorResponsavelId = colaboradorResponsavelId;
    }

}
