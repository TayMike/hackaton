package com.fiap.hackaton.entity.hospital.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
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
public class Hospital extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NonNull
    private List<Colaborador> colaboradores;
    @NonNull
    private String cep;
    @NonNull
    private Integer numero;

    public void setNumero(@NonNull Integer numero) {
        this.numero = numero;
    }

    public void setCep(@NonNull String cep) {
        this.cep = cep;
    }

    public void setColaboradores(@NonNull List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

}
