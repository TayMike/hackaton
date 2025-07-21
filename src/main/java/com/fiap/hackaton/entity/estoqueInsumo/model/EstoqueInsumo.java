package com.fiap.hackaton.entity.estoqueInsumo.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EstoqueInsumo extends AbstractEntity {

    @NonNull
    private List<Insumo> itens;
    @NonNull
    private List<Long> quantidades;
    @NonNull
    private Hospital hospital;

    public EstoqueInsumo(@NonNull List<Insumo> itens, @NonNull List<Long> quantidades, @NonNull Hospital hospital) {
        this.itens = itens;
        this.quantidades = quantidades;
        this.hospital = hospital;
    }

}