package com.fiap.hackaton.entity.coletaEquipamento.model;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;
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
public class ColetaEquipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter
    private UUID id;
    @NonNull
    private List<Equipamento> equipamentos;
    @NonNull
    private List<Long> quantidades;
    @NonNull
    private Colaborador colaboradorEntregador;
    @NonNull
    private LocalDateTime dataHoraColeta;
    @NonNull
    private Colaborador colaboradorResponsavel;
    @NonNull
    private Hospital hospital;

    public ColetaEquipamento(@NonNull List<Equipamento> equipamentos, @NonNull List<Long> quantidades, @NonNull Colaborador colaboradorEntregador, @NonNull LocalDateTime dataHoraColeta, @NonNull Colaborador colaboradorResponsavel, @NonNull Hospital hospital) {
        this.equipamentos = equipamentos;
        this.quantidades = quantidades;
        this.colaboradorEntregador = colaboradorEntregador;
        this.dataHoraColeta = dataHoraColeta;
        this.colaboradorResponsavel = colaboradorResponsavel;
        this.hospital = hospital;
    }

}
