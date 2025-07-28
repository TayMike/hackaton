package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;
import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.embeddable.QuantidadeInsumoEmbeddable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "entrega_insumo")
public class EntregaInsumoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "entrega_quantidade_insumo", joinColumns = @JoinColumn(name = "entrega_id"))
    private List<QuantidadeInsumoEmbeddable> insumos;

    @Column(name = "colaborador_id", nullable = false)
    private UUID colaboradorRecebedorId;

    @Column(name = "data_hora_recebimento", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private OffsetDateTime dataHoraRecebimento;

    @Column(name = "hospital_id", nullable = false)
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