package com.fiap.hackaton.entity.estoqueInsumo.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
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

@Entity
@Getter
@NoArgsConstructor(force = true)
public class EstoqueInsumo extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter
    private UUID id;
    @NonNull
    private List<Insumo> itens;
    @NonNull
    private List<Long> quantidades;

    public EstoqueInsumo(@NonNull List<Insumo> itens, @NonNull List<Long> quantidades, @NonNull Hospital hospital) {
        this.itens = itens;
        this.quantidades = quantidades;
        this.hospital = hospital;
    }

    public void setHospital(@NonNull Hospital hospital) {
        this.hospital = hospital;
    }

    public void setQuantidades(@NonNull List<Long> quantidades) {
        this.quantidades = quantidades;
    }

    public void setItens(@NonNull List<Insumo> itens) {
        this.itens = itens;
    }

    @NonNull
    private Hospital hospital;

}