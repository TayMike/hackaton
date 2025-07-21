package com.fiap.hackaton.entity.coletaInsumo.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ColetaInsumo extends AbstractEntity {

    @NonNull
    private List<Insumo> insumos;
    @NonNull
    private List<Long> quantidades;
    @NonNull
    private Colaborador colaboradorEntregador;
    @NonNull
    private OffsetDateTime dataHoraColeta;
    @NonNull
    private Paciente pacienteRecebedor;
    @NonNull
    private Hospital hospital;

    public ColetaInsumo(@NonNull List<Insumo> insumos, @NonNull List<Long> quantidades, @NonNull Colaborador colaboradorEntregador, @NonNull OffsetDateTime dataHoraColeta, @NonNull Paciente pacienteRecebedor, @NonNull Hospital hospital) {
        this.insumos = insumos;
        this.quantidades = quantidades;
        this.colaboradorEntregador = colaboradorEntregador;
        this.dataHoraColeta = dataHoraColeta;
        this.pacienteRecebedor = pacienteRecebedor;
        this.hospital = hospital;
    }
}
