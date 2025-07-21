package com.fiap.hackaton.entity.hospital.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Hospital extends AbstractEntity {

    private List<Colaborador> colaboradores;
    @NonNull
    private String cep;
    @NonNull
    private Integer numero;
    @NonNull
    private Integer quantidadeLeitoAtual;
    @NonNull
    private Integer quantidadeLeitoMaximo;

    public Hospital(List<Colaborador> colaboradores, @NonNull String cep, @NonNull Integer numero, @NonNull Integer quantidadeLeitoAtual, @NonNull Integer quantidadeLeitoMaximo) {
        this.colaboradores = colaboradores;
        this.cep = cep;
        this.numero = numero;
        this.quantidadeLeitoAtual = quantidadeLeitoAtual;
        this.quantidadeLeitoMaximo = quantidadeLeitoMaximo;
    }
}