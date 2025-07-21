package com.fiap.hackaton.entity.descarteEquipamento.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
    private LocalDateTime dataHoraDescarte;
    @NonNull
    private Hospital hospital;

    public DescarteEquipamento(@NonNull List<Equipamento> equipamentos, @NonNull List<Long> quantidade, @NonNull Colaborador colaboradorResponsavel, @NonNull LocalDateTime dataHoraDescarte, @NonNull Hospital hospital) {
        this.equipamentos = equipamentos;
        this.quantidade = quantidade;
        this.colaboradorResponsavel = colaboradorResponsavel;
        this.dataHoraDescarte = dataHoraDescarte;
        this.hospital = hospital;
    }

}
