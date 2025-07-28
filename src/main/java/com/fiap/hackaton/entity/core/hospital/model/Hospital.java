package com.fiap.hackaton.entity.core.hospital.model;

import com.fiap.hackaton.entity.auxiliar.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Hospital extends AbstractEntity {

    @NonNull
    private String nome;
    @NonNull
    private String cnpj;
    private List<UUID> colaboradoresIds;
    @NonNull
    private String cep;
    @NonNull
    private Integer numero;
    @NonNull
    private Integer quantidadeLeitoAtual;
    @NonNull
    private Integer quantidadeLeitoMaximo;

    public Hospital(@NonNull String nome, @NonNull String cnpj, List<UUID> colaboradoresIds, @NonNull String cep, @NonNull Integer numero, @NonNull Integer quantidadeLeitoAtual, @NonNull Integer quantidadeLeitoMaximo) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.colaboradoresIds = colaboradoresIds;
        this.cep = cep;
        this.numero = numero;
        this.quantidadeLeitoAtual = quantidadeLeitoAtual;
        this.quantidadeLeitoMaximo = quantidadeLeitoMaximo;
    }
}