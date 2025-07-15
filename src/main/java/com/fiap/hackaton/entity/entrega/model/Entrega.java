package com.fiap.hackaton.entity.entrega.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Getter
public class Entrega extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private final List<Insumo> insumo;
    private final List<Long> quantidade;
    private final Colaborador colaboradorRecebedor;
    private final LocalDateTime dataHoraRecebimento;
    private final Hospital hospital;

}