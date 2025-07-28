package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.embeddable;

import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.model.EstoqueInsumo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class QuantidadeInsumoEmbeddable {

    @Column(name = "insumo_id", nullable = false)
    private UUID insumoId;

    @Column(name = "quantidade", nullable = false)
    private Long quantidade;

    public QuantidadeInsumoEmbeddable(UUID insumoId, Long quantidade) {
        this.insumoId = insumoId;
        this.quantidade = quantidade;
    }
}