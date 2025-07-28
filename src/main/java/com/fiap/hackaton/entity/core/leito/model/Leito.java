package com.fiap.hackaton.entity.core.leito.model;

import com.fiap.hackaton.entity.auxiliar.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Leito extends AbstractEntity {

    @NonNull
    private String identificacao;
    @NonNull
    private String pavilhao;
    @NonNull
    private String quarto;
    @NonNull
    private UUID hospitalId;
    private UUID pacienteId;

    public Leito(@NonNull String identificacao, @NonNull String pavilhao, @NonNull String quarto, @NonNull UUID hospitalId, UUID pacienteId) {
        this.identificacao = identificacao;
        this.pavilhao = pavilhao;
        this.quarto = quarto;
        this.hospitalId = hospitalId;
        this.pacienteId = pacienteId;
    }
}