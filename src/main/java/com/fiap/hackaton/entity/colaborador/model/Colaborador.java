package com.fiap.hackaton.entity.colaborador.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.IPessoa;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Colaborador extends AbstractEntity implements IPessoa {

    @NonNull
    private String cpf;
    @NonNull
    private String nome;
    @NonNull
    private String matricula;
    @NonNull
    private OffsetDateTime primeiroDiaCadastro;
    @NonNull
    private String cep;
    @NonNull
    private Integer numeroCasa;
    @NonNull
    private Hospital hospital;
    @NonNull
    private String setor;
    @NonNull
    private Boolean ativo;

    public Colaborador(@NonNull String cpf, @NonNull String nome, @NonNull String matricula, @NonNull OffsetDateTime primeiroDiaCadastro, @NonNull String cep, @NonNull Integer numeroCasa, @NonNull Hospital hospital, @NonNull String setor, @NonNull Boolean ativo) {
        this.cpf = cpf;
        this.nome = nome;
        this.matricula = matricula;
        this.primeiroDiaCadastro = primeiroDiaCadastro;
        this.cep = cep;
        this.numeroCasa = numeroCasa;
        this.hospital = hospital;
        this.setor = setor;
        this.ativo = ativo;
    }

}
