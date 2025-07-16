package com.fiap.hackaton.entity.estoque.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Getter
public class Estoque<T> extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NonNull
    private List<T> itens;
    @NonNull
    private List<Long> quantidades;

    public void setHospital(@NonNull Hospital hospital) {
        this.hospital = hospital;
    }

    public void setQuantidades(@NonNull List<Long> quantidades) {
        this.quantidades = quantidades;
    }

    public void setItens(@NonNull List<T> itens) {
        this.itens = itens;
    }

    @NonNull
    private Hospital hospital;

}
