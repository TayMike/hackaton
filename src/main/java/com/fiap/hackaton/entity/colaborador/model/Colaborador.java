package com.fiap.hackaton.entity.colaborador.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.IPessoa;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Colaborador extends AbstractEntity<Long> implements IPessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter
    private UUID id;
    @NonNull
    private String cpf;
    @NonNull
    private String nome;
    @NonNull
    private String matricula;
    @NonNull
    private LocalDateTime primeiroDiaCadastro;
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

    public Colaborador(@NonNull String cpf, @NonNull String nome, @NonNull String matricula, @NonNull LocalDateTime primeiroDiaCadastro, @NonNull String cep, @NonNull Integer numeroCasa, @NonNull Hospital hospital, @NonNull String setor, @NonNull Boolean ativo) {
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

    public void setAtivo(@NonNull Boolean ativo) {
        this.ativo = ativo;
    }

    public void setSetor(@NonNull String setor) {
        this.setor = setor;
    }

    public void setHospital(@NonNull Hospital hospital) {
        this.hospital = hospital;
    }

    public void setNumeroCasa(@NonNull Integer numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public void setCep(@NonNull String cep) {
        this.cep = cep;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

}
