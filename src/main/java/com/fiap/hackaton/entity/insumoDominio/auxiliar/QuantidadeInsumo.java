package com.fiap.hackaton.entity.insumoDominio.auxiliar;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
public class QuantidadeInsumo implements Serializable {

    @NonNull
    private UUID insumoId;
    @NonNull
    private Long quantidade;

    public QuantidadeInsumo(@NonNull UUID insumoId, @NonNull Long quantidade) {
        this.insumoId = insumoId;
        this.quantidade = quantidade;
    }
}

