package com.fiap.hackaton.entity.equipamento.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Equipamento extends AbstractEntity {

    @NonNull
    private String nome;
    @NonNull
    private BigDecimal custo;
    @NonNull
    private OffsetDateTime tempoGarantia;
    @NonNull
    private OffsetDateTime proximaManutencaoPreventiva;
    @NonNull
    private String marca;
    @NonNull
    private Hospital hospital;

    public Equipamento(@NonNull String nome, @NonNull BigDecimal custo, @NonNull OffsetDateTime tempoGarantia, @NonNull OffsetDateTime proximaManutencaoPreventiva, @NonNull String marca, @NonNull Hospital hospital) {
        this.nome = nome;
        this.custo = custo;
        this.tempoGarantia = tempoGarantia;
        this.proximaManutencaoPreventiva = proximaManutencaoPreventiva;
        this.marca = marca;
        this.hospital = hospital;
    }

}