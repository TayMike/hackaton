package com.fiap.hackaton.entity.insumo.model;

import com.fiap.hackaton.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
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
    private final String nome;
    private final BigDecimal custo;
    private final Long quantidade;
    private final Long peso;
    private final LocalDate validade;
    private final String marca;

    @Enumerated(EnumType.STRING)
    private final Medida unidadeMedida;

    public enum Medida {
        MG, ML
    }

}
