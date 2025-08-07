package com.fiap.hackaton.entity.equipamentoDominio.equipamento.model;

import com.fiap.hackaton.entity.auxiliar.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Equipamento extends AbstractEntity implements Serializable {

    @NonNull
    private String nome;
    @NonNull
    private BigDecimal custo;
    @NonNull
    private OffsetDateTime tempoGarantia;
    @NonNull
    private OffsetDateTime proximaManutencaoPreventiva;
    @NonNull
    private String numeroSerie;
    @NonNull
    private String marca;
    private OffsetDateTime descarte;

    public Equipamento(@NonNull String nome, @NonNull BigDecimal custo, @NonNull OffsetDateTime tempoGarantia, @NonNull OffsetDateTime proximaManutencaoPreventiva, String numeroSerie, @NonNull String marca, OffsetDateTime descarte) {
        this.nome = nome;
        this.custo = custo;
        this.tempoGarantia = tempoGarantia;
        this.proximaManutencaoPreventiva = proximaManutencaoPreventiva;
        this.numeroSerie = numeroSerie;
        this.marca = marca;
        this.descarte = descarte;
    }

}