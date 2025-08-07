package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;
import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.embeddable.QuantidadeInsumoEmbeddable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "entrega_insumo",
        indexes = {
                @Index(name = "idx_entrega_insumo_colaborador_id", columnList = "colaborador_id"),
                @Index(name = "idx_entrega_insumo_hospital_id", columnList = "hospital_id")
        }
)
public class EntregaInsumoSchema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "entrega_quantidade_insumo", joinColumns = @JoinColumn(name = "entrega_id"))
    @NotEmpty(message = "A lista de insumos não pode estar vazia")
    private List<QuantidadeInsumoEmbeddable> insumos;

    @Column(name = "colaborador_id", nullable = false)
    @NotNull(message = "Colaborador recebedor ID não pode ser nulo")
    private UUID colaboradorRecebedorId;

    @Column(name = "data_hora_recebimento", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @NotNull(message = "Data e hora de recebimento não podem ser nulas")
    private OffsetDateTime dataHoraRecebimento;

    @Column(name = "hospital_id", nullable = false)
    @NotNull(message = "Hospital ID não pode ser nulo")
    private UUID hospitalId;

    public EntregaInsumoSchema(EntregaInsumo entregaInsumo) {
        this.id = entregaInsumo.getId();
        this.insumos = entregaInsumo.getInsumos().stream().map((i) -> new QuantidadeInsumoEmbeddable(i.getInsumoId(), i.getQuantidade())).toList();
        this.colaboradorRecebedorId = entregaInsumo.getColaboradorRecebedorId();
        this.dataHoraRecebimento = entregaInsumo.getDataHoraRecebimento();
        this.hospitalId = entregaInsumo.getHospitalId();
    }

    public EntregaInsumo toEntity() {
        List<QuantidadeInsumo> insumosConvertidos = this.insumos.stream()
                .map(i -> new QuantidadeInsumo(i.getInsumoId(), i.getQuantidade()))
                .toList();
        EntregaInsumo entrega = new EntregaInsumo(
                insumosConvertidos,
                this.colaboradorRecebedorId,
                this.dataHoraRecebimento,
                this.hospitalId
        );
        entrega.setId(this.id);
        return entrega;
    }
}