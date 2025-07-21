package com.fiap.hackaton.entity.estoqueEquipamento.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class EstoqueEquipamento extends AbstractEntity {

    @NonNull
    private List<Equipamento> itens;
    @NonNull
    private List<Long> quantidades;
    @NonNull
    private Hospital hospital;

    public EstoqueEquipamento(@NonNull List<Equipamento> itens, @NonNull List<Long> quantidades, @NonNull Hospital hospital) {
        this.itens = itens;
        this.quantidades = quantidades;
        this.hospital = hospital;
    }

}
