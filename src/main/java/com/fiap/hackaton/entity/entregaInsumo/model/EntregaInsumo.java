package com.fiap.hackaton.entity.entregaInsumo.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(force = true)
public class EntregaInsumo extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter
    private UUID id;
    @NonNull
    private List<Insumo> insumo;
    @NonNull
    private List<Long> quantidade;
    @NonNull
    private Colaborador colaboradorRecebedor;
    @NonNull
    private LocalDateTime dataHoraRecebimento;
    @NonNull
    private Hospital hospital;

    public EntregaInsumo(@NonNull List<Insumo> insumo, @NonNull List<Long> quantidade, @NonNull Colaborador colaboradorRecebedor, @NonNull LocalDateTime dataHoraRecebimento, @NonNull Hospital hospital) {
        this.insumo = insumo;
        this.quantidade = quantidade;
        this.colaboradorRecebedor = colaboradorRecebedor;
        this.dataHoraRecebimento = dataHoraRecebimento;
        this.hospital = hospital;
    }
}