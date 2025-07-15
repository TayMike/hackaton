package com.fiap.hackaton.entity.colaborador.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.hospital.model.Hospital;
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
public class Colaborador extends AbstractEntity<Long> implements IPessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private final String cpf;
    private final String nome;
    private final String matricula;
    private final LocalDateTime primeiroDiaCadastro;
    private final String cep;
    private final Integer numeroCasa;
    private final Hospital hospital;
    private final String setor;
    private final Boolean ativo;

}
