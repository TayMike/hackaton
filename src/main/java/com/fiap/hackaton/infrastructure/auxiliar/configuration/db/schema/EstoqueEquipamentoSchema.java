package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.model.EstoqueEquipamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "estoque_equipamento",
        indexes = {
                @Index(name = "idx_estoque_equipamento_id", columnList = "equipamento_id"),
                @Index(name = "idx_estoque_hospital_id", columnList = "hospital_id"),
                @Index(name = "idx_estoque_colaborador_entregador_id", columnList = "colaborador_entregador_id"),
                @Index(name = "idx_estoque_colaborador_responsavel_id", columnList = "colaborador_responsavel_id")
        }
)
public class EstoqueEquipamentoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "equipamento_id", nullable = false)
    @NotNull(message = "Equipamento ID não pode ser nulo")
    private UUID equipamentoId;

    @Column(name = "hospital_id", nullable = false)
    @NotNull(message = "Hospital ID não pode ser nulo")
    private UUID hospitalId;

    @Column(name = "colaborador_entregador_id")
    private UUID colaboradorEntregadorId;

    @Column(name = "data_hora_coleta")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime dataHoraColeta;

    @Column(name = "colaborador_responsavel_id")
    private UUID colaboradorResponsavelId;

    public EstoqueEquipamentoSchema(EstoqueEquipamento estoqueEquipamento) {
        this.id = estoqueEquipamento.getId();
        this.equipamentoId = estoqueEquipamento.getEquipamentoId();
        this.hospitalId = estoqueEquipamento.getHospitalId();
        this.colaboradorEntregadorId = estoqueEquipamento.getColaboradorEntregadorId();
        this.dataHoraColeta = estoqueEquipamento.getDataHoraColeta();
        this.colaboradorResponsavelId = estoqueEquipamento.getColaboradorResponsavelId();
    }

    public EstoqueEquipamento toEntity() {
        EstoqueEquipamento estoqueEquipamento = new EstoqueEquipamento(
                equipamentoId,
                hospitalId,
                colaboradorEntregadorId,
                dataHoraColeta,
                colaboradorResponsavelId
        );
        estoqueEquipamento.setId(this.id);
        return estoqueEquipamento;
    }
}