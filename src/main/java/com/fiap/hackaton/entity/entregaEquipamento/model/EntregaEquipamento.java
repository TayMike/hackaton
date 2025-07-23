package com.fiap.hackaton.entity.entregaEquipamento.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class EntregaEquipamento extends AbstractEntity {

    @NonNull
    private List<Equipamento> equipamentos;
    @NonNull
    private List<Long> quantidade;
    @NonNull
    private Colaborador colaboradorRecebedor;
    @NonNull
    private OffsetDateTime dataHoraRecebimento;
    @NonNull
    private Hospital hospital;

    public EntregaEquipamento(@NonNull List<Equipamento> equipamentos, @NonNull List<Long> quantidade, @NonNull Colaborador colaboradorRecebedor, @NonNull OffsetDateTime dataHoraRecebimento, @NonNull Hospital hospital) {
        this.equipamentos = equipamentos;
        this.quantidade = quantidade;
        this.colaboradorRecebedor = colaboradorRecebedor;
        this.dataHoraRecebimento = dataHoraRecebimento;
        this.hospital = hospital;
    }

}
