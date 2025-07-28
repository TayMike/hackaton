package com.fiap.hackaton.entity.core.colaborador.model;

import com.fiap.hackaton.entity.auxiliar.AbstractEntity;
import com.fiap.hackaton.entity.core.auxiliar.IPessoa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

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
    private OffsetDateTime ultimoDiaCadastro;
    @NonNull
    private String cep;
    @NonNull
    private Integer numeroCasa;
    @NonNull
    private UUID hospitalId;
    @NonNull
    private String setor;

    public Colaborador(@NonNull String cpf, @NonNull String nome, @NonNull String matricula, @NonNull String cep, @NonNull Integer numeroCasa, @NonNull UUID hospitalId, @NonNull String setor, @NonNull OffsetDateTime primeiroDiaCadastro, OffsetDateTime ultimoDiaCadastro) {
        this.cpf = cpf;
        this.nome = nome;
        this.matricula = matricula;
        this.primeiroDiaCadastro = primeiroDiaCadastro;
        this.cep = cep;
        this.numeroCasa = numeroCasa;
        this.hospitalId = hospitalId;
        this.setor = setor;
    }

}
