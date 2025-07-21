package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fiap.hackaton.entity.entregaInsumo.model.EntregaInsumo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "entrega")
public class EntregaInsumoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToMany
    @JoinTable(
            name = "entrega_insumo",
            joinColumns = @JoinColumn(name = "entrega_id"),
            inverseJoinColumns = @JoinColumn(name = "insumo_id")
    )
    private List<InsumoSchema> insumo = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "entrega_quantidade", joinColumns = @JoinColumn(name = "entrega_id"))
    @Column(name = "quantidade", nullable = false)
    private List<Long> quantidade = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "colaborador_id", nullable = false)
    private ColaboradorSchema colaboradorRecebedor;

    @Column(name = "data_hora_recebimento", nullable = false)
    private OffsetDateTime dataHoraRecebimento;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalSchema hospital;

    public EntregaInsumoSchema(EntregaInsumo entregaInsumo, List<InsumoSchema> insumos, ColaboradorSchema colaboradorRecebedorSchema, HospitalSchema hospitalSchema) {
        this.id = entregaInsumo.getId();
        this.insumo = insumos;
        this.quantidade = entregaInsumo.getQuantidade();
        this.colaboradorRecebedor = colaboradorRecebedorSchema;
        this.dataHoraRecebimento = entregaInsumo.getDataHoraRecebimento();
        this.hospital = hospitalSchema;
    }

    public EntregaInsumo toEntrega() {
        EntregaInsumo entregaInsumo = new EntregaInsumo(
                this.insumo.stream().map(InsumoSchema::toInsumo).collect(Collectors.toList()),
                this.quantidade,
                this.colaboradorRecebedor.toColaborador(),
                this.dataHoraRecebimento,
                this.hospital.toHospital()
        );
        entregaInsumo.setId(this.id);
        return entregaInsumo;
    }
}