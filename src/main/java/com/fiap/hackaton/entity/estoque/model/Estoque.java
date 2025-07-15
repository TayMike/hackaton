package com.fiap.hackaton.entity.estoque.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Getter
public class Estoque<T> extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private final List<T> itens;
    private final List<Long> quantidades;
    private final Hospital hospital;

}
