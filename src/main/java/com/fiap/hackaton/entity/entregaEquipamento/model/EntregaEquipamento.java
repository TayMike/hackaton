package com.fiap.hackaton.entity.entregaEquipamento.model;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;
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
@NoArgsConstructor
public class EntregaEquipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter
    private UUID id;
    @NonNull
    private List<Equipamento> equipamentos;
    @NonNull
    private List<Long> quantidade;
    @NonNull
    private Colaborador colaboradorRecebedor;
    @NonNull
    private LocalDateTime dataHoraRecebimento;
    @NonNull
    private Hospital hospital;

    public EntregaEquipamento(@NonNull List<Equipamento> equipamentos, @NonNull List<Long> quantidade, @NonNull Colaborador colaboradorRecebedor, @NonNull LocalDateTime dataHoraRecebimento, @NonNull Hospital hospital) {
        this.equipamentos = equipamentos;
        this.quantidade = quantidade;
        this.colaboradorRecebedor = colaboradorRecebedor;
        this.dataHoraRecebimento = dataHoraRecebimento;
        this.hospital = hospital;
    }

}
