package com.fiap.hackaton.entity.leito.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Leito extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NonNull
    private String identificacao;
    @NonNull
    private String pavilhao;
    @NonNull
    private String quarto;
    @NonNull
    private Hospital hospital;
    private Paciente paciente;

    public Leito(@NonNull String identificacao, @NonNull String pavilhao, @NonNull String quarto, @NonNull Hospital hospital, Paciente paciente) {
        this.identificacao = identificacao;
        this.pavilhao = pavilhao;
        this.quarto = quarto;
        this.hospital = hospital;
        this.paciente = paciente;
    }
}