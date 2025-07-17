package com.fiap.hackaton.entity.equipamento.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Equipamento extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NonNull
    private String nome;
    @NonNull
    private BigDecimal custo;
    @NonNull
    private LocalDate tempoGarantia;
    @NonNull
    private LocalDate proximaManutencaoPreventiva;
    @NonNull
    private String marca;
    @NonNull
    private Hospital hospital;

    public Equipamento(@NonNull String nome, @NonNull BigDecimal custo, @NonNull LocalDate tempoGarantia, @NonNull LocalDate proximaManutencaoPreventiva, @NonNull String marca, @NonNull Hospital hospital) {
        this.nome = nome;
        this.custo = custo;
        this.tempoGarantia = tempoGarantia;
        this.proximaManutencaoPreventiva = proximaManutencaoPreventiva;
        this.marca = marca;
        this.hospital = hospital;
    }

}