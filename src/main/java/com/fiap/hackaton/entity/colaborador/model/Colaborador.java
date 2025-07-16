package com.fiap.hackaton.entity.colaborador.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.IPessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Getter
public class Colaborador extends AbstractEntity<Long> implements IPessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private final String cpf;
    @NonNull
    private String nome;
    private final String matricula;
    private final LocalDateTime primeiroDiaCadastro;
    @NonNull
    private String cep;
    @NonNull
    private Integer numeroCasa;
    @NonNull
    private Hospital hospital;
    @NonNull
    private String setor;

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

    @NonNull
    private Boolean ativo;

}
