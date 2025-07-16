package com.fiap.hackaton.entity.insumo.model;

import com.fiap.hackaton.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Getter
public class Insumo extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NonNull
    private String nome;
    @NonNull
    private BigDecimal custo;
    @NonNull
    private Long quantidade;
    @NonNull
    private Long peso;
    @NonNull
    private LocalDate validade;
    @NonNull
    private String marca;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Medida unidadeMedida;

    public enum Medida {
        MG, ML
    }

    public void setUnidadeMedida(@NonNull Medida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public void setMarca(@NonNull String marca) {
        this.marca = marca;
    }

    public void setValidade(@NonNull LocalDate validade) {
        this.validade = validade;
    }

    public void setPeso(@NonNull Long peso) {
        this.peso = peso;
    }

    public void setQuantidade(@NonNull Long quantidade) {
        this.quantidade = quantidade;
    }

    public void setCusto(@NonNull BigDecimal custo) {
        this.custo = custo;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

}
