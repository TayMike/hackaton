package com.fiap.hackaton.entity.coletaEquipamento.model;

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
public class ColetaEquipamento extends AbstractEntity {

    @NonNull
    private List<Equipamento> equipamentos;
    @NonNull
    private List<Long> quantidades;
    @NonNull
    private Colaborador colaboradorEntregador;
    @NonNull
    private OffsetDateTime dataHoraColeta;
    @NonNull
    private Colaborador colaboradorResponsavel;
    @NonNull
    private Hospital hospital;

    public ColetaEquipamento(@NonNull List<Equipamento> equipamentos, @NonNull List<Long> quantidades, @NonNull Colaborador colaboradorEntregador, @NonNull OffsetDateTime dataHoraColeta, @NonNull Colaborador colaboradorResponsavel, @NonNull Hospital hospital) {
        this.equipamentos = equipamentos;
        this.quantidades = quantidades;
        this.colaboradorEntregador = colaboradorEntregador;
        this.dataHoraColeta = dataHoraColeta;
        this.colaboradorResponsavel = colaboradorResponsavel;
        this.hospital = hospital;
    }

}
