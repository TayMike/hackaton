package com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.model;

import com.fiap.hackaton.entity.auxiliar.AbstractEntity;
import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;
import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.exception.EstoqueInsumoNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.exception.InsufficientStockException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class EstoqueInsumo extends AbstractEntity {

    @NonNull
    private List<QuantidadeInsumo> insumos;
    @NonNull
    private UUID hospitalId;

    private EstoqueInsumo(@NonNull List<QuantidadeInsumo> insumos, @NonNull UUID hospitalId) {
        this.insumos = insumos;
        this.hospitalId = hospitalId;
    }

    public void aumentarComEntrega(EntregaInsumo entrega) {
        for (QuantidadeInsumo recebido : entrega.getInsumos()) {
            boolean encontrado = false;
            for (QuantidadeInsumo existente : this.insumos) {
                if (existente.getInsumoId().equals(recebido.getInsumoId())) {
                    existente.setQuantidade(existente.getQuantidade() + recebido.getQuantidade());
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                this.insumos.add(new QuantidadeInsumo(recebido.getInsumoId(), recebido.getQuantidade()));
            }
        }
    }

    public void diminuirComColeta(ColetaInsumo coleta) throws EstoqueInsumoNotFoundException, InsufficientStockException {
        for (QuantidadeInsumo coletado : coleta.getInsumos()) {
            boolean encontrado = false;
            for (QuantidadeInsumo existente : this.insumos) {
                if (existente.getInsumoId().equals(coletado.getInsumoId())) {
                    long novaQuantidade = existente.getQuantidade() - coletado.getQuantidade();
                    if (novaQuantidade < 0) {
                        throw new InsufficientStockException(String.valueOf(existente.getInsumoId()));
                    }
                    existente.setQuantidade(novaQuantidade);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                throw new EstoqueInsumoNotFoundException(String.valueOf(coletado.getInsumoId()));
            }
        }
    }

}