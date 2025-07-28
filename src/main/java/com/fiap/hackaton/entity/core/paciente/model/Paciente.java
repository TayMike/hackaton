package com.fiap.hackaton.entity.core.paciente.model;

import com.fiap.hackaton.entity.auxiliar.AbstractEntity;
import com.fiap.hackaton.entity.core.auxiliar.IPessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor
public class Paciente extends AbstractEntity implements IPessoa {

    @NonNull
    private String cpf;
    @NonNull
    private String nome;
    @NonNull
    private OffsetDateTime primeiroDiaCadastro;
    @NonNull
    private String cep;
    @NonNull
    private Integer numeroCasa;

    public Paciente(@NonNull String cpf, @NonNull String nome, @NonNull OffsetDateTime primeiroDiaCadastro, @NonNull String cep, @NonNull Integer numeroCasa) {
        this.cpf = cpf;
        this.nome = nome;
        this.primeiroDiaCadastro = primeiroDiaCadastro;
        this.cep = cep;
        this.numeroCasa = numeroCasa;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    public void setCep(@NonNull String cep) {
        this.cep = cep;
    }

    public void setNumeroCasa(@NonNull Integer numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

}
