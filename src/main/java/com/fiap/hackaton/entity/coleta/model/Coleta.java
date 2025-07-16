package com.fiap.hackaton.entity.coleta.model;

import com.fiap.hackaton.entity.AbstractEntity;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
public class Coleta extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter
    private UUID id;
    @NonNull
    private List<Insumo> insumos;
    @NonNull
    private List<Long> quantidades;
    @NonNull
    private Colaborador colaboradorEntregador;
    @NonNull
    private LocalDateTime dataHoraColeta;
    @NonNull
    private Paciente pacienteRecebedor;
    @NonNull
    private Hospital hospital;

    public Coleta(@NonNull List<Insumo> insumos, @NonNull List<Long> quantidades, @NonNull Colaborador colaboradorEntregador, @NonNull LocalDateTime dataHoraColeta, @NonNull Paciente pacienteRecebedor, @NonNull Hospital hospital) {
        this.insumos = insumos;
        this.quantidades = quantidades;
        this.colaboradorEntregador = colaboradorEntregador;
        this.dataHoraColeta = dataHoraColeta;
        this.pacienteRecebedor = pacienteRecebedor;
        this.hospital = hospital;
    }
}
