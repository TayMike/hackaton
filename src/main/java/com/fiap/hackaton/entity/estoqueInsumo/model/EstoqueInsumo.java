package com.fiap.hackaton.entity.estoqueInsumo.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class EstoqueInsumo extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
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