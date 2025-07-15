package com.fiap.hackaton.entity.paciente.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.IPessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Getter
public class Paciente extends AbstractEntity<Long> implements IPessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private final String cpf;
    private final String nome;
    private final LocalDateTime primeiroDiaCadastro;
    private final String cep;
    private final Integer numeroCasa;

}
