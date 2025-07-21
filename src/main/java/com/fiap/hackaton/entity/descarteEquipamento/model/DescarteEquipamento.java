package com.fiap.hackaton.entity.descarteEquipamento.model;

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
public class DescarteEquipamento extends AbstractEntity {

    @NonNull
    private List<Equipamento> equipamentos;
    @NonNull
    private List<Long> quantidade;
    @NonNull
    private Colaborador colaboradorResponsavel;
    @NonNull
    private OffsetDateTime dataHoraDescarte;
    @NonNull
    private Hospital hospital;

    public DescarteEquipamento(@NonNull List<Equipamento> equipamentos, @NonNull List<Long> quantidade, @NonNull Colaborador colaboradorResponsavel, @NonNull OffsetDateTime dataHoraDescarte, @NonNull Hospital hospital) {
        this.equipamentos = equipamentos;
        this.quantidade = quantidade;
        this.colaboradorResponsavel = colaboradorResponsavel;
        this.dataHoraDescarte = dataHoraDescarte;
        this.hospital = hospital;
    }

}
