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
@Setter
@NoArgsConstructor(force = true)
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
    @NonNull
    private Integer quantidadeLeitoAtual;
    @NonNull
    private Integer quantidadeLeitoMaximo;

    public Hospital(@NonNull List<Colaborador> colaboradores, @NonNull String cep, @NonNull Integer numero, @NonNull Integer quantidadeLeitoAtual, @NonNull Integer quantidadeLeitoMaximo) {
        this.colaboradores = colaboradores;
        this.cep = cep;
        this.numero = numero;
        this.quantidadeLeitoAtual = quantidadeLeitoAtual;
        this.quantidadeLeitoMaximo = quantidadeLeitoMaximo;
    }

}
