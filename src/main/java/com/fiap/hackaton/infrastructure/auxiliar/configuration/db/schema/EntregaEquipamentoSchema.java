package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.model.EntregaEquipamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "entrega_equipamentoId")
public class EntregaEquipamentoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "equipamento_id", nullable = false)
    private UUID equipamentoId;

    @Column(name = "colaborador_recebedor_id", nullable = false)
    private UUID colaboradorRecebedorId;

    @Column(name = "data_hora_recebimento", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private OffsetDateTime dataHoraRecebimento;

    @Column(name = "hospital_id", nullable = false)
    private UUID hospitalId;

    public EntregaEquipamentoSchema(EntregaEquipamento entregaEquipamento) {
        this.id = entregaEquipamento.getId();
        this.equipamentoId = entregaEquipamento.getEquipamentoId();
        this.colaboradorRecebedorId = entregaEquipamento.getColaboradorRecebedorId();
        this.dataHoraRecebimento = entregaEquipamento.getDataHoraRecebimento();
        this.hospitalId = entregaEquipamento.getHospitalId();
    }

    public EntregaEquipamento toEntity() {
        EntregaEquipamento entregaEquipamento = new EntregaEquipamento(
                this.equipamentoId,
                this.colaboradorRecebedorId,
                this.dataHoraRecebimento,
                this.hospitalId
        );
        entregaEquipamento.setId(this.id);
        return entregaEquipamento;
    }
}