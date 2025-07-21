package com.fiap.hackaton.entity.entregaInsumo.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class EntregaInsumo extends AbstractEntity {

    @NonNull
    private List<Insumo> insumo;
    @NonNull
    private List<Long> quantidade;
    @NonNull
    private Colaborador colaboradorRecebedor;
    @NonNull
    private OffsetDateTime dataHoraRecebimento;
    @NonNull
    private Hospital hospital;

    public EntregaInsumo(@NonNull List<Insumo> insumo, @NonNull List<Long> quantidade, @NonNull Colaborador colaboradorRecebedor, @NonNull OffsetDateTime dataHoraRecebimento, @NonNull Hospital hospital) {
        this.insumo = insumo;
        this.quantidade = quantidade;
        this.colaboradorRecebedor = colaboradorRecebedor;
        this.dataHoraRecebimento = dataHoraRecebimento;
        this.hospital = hospital;
    }
}