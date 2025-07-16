package com.fiap.hackaton.entity.hospital.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
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
public class Hospital extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter
    private UUID id;
    @NonNull
    private List<Colaborador> colaboradores;
    @NonNull
    private String cep;
    @NonNull
    private Integer numero;

    public Hospital(@NonNull List<Colaborador> colaboradores, @NonNull String cep, @NonNull Integer numero) {
        this.colaboradores = colaboradores;
        this.cep = cep;
        this.numero = numero;
    }

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
