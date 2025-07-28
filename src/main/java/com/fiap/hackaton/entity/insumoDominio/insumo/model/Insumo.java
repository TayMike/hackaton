package com.fiap.hackaton.entity.insumoDominio.insumo.model;

import com.fiap.hackaton.entity.auxiliar.AbstractEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Insumo extends AbstractEntity {

    @NonNull
    private String nome;
    @NonNull
    private BigDecimal custo;
    @NonNull
    private Long quantidade;
    @NonNull
    private Long peso;
    @NonNull
    private OffsetDateTime validade;
    @NonNull
    private String marca;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Medida unidadeMedida;

    public enum Medida {
        MG, ML
    }

    public Insumo(@NonNull String nome, @NonNull BigDecimal custo, @NonNull Long quantidade, @NonNull Long peso, @NonNull OffsetDateTime validade, @NonNull String marca, @NonNull Medida unidadeMedida) {
        this.nome = nome;
        this.custo = custo;
        this.quantidade = quantidade;
        this.peso = peso;
        this.validade = validade;
        this.marca = marca;
        this.unidadeMedida = unidadeMedida;
    }

}