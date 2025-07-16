package com.fiap.hackaton.entity.paciente.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.IPessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(force = true)
public class Paciente extends AbstractEntity<Long> implements IPessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter
    private UUID id;
    @NonNull
    private String cpf;
    @NonNull
    private String nome;
    @NonNull
    private LocalDateTime primeiroDiaCadastro;
    @NonNull
    private String cep;
    @NonNull
    private Integer numeroCasa;

    public Paciente(@NonNull String cpf, @NonNull String nome, @NonNull LocalDateTime primeiroDiaCadastro, @NonNull String cep, @NonNull Integer numeroCasa) {
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
