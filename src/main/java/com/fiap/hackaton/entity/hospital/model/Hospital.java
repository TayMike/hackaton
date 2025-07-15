package com.fiap.hackaton.entity.hospital.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Getter
public class Hospital extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private final List<Colaborador> colaboradores;
    private final String cep;
    private final Integer numero;

}
