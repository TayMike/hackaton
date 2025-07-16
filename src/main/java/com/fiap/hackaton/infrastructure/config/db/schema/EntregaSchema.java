package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fiap.hackaton.entity.entrega.model.Entrega;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Entrega")
public class EntregaSchema extends AbstractEntitySchema<UUID> {

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
    private LocalDateTime dataHoraRecebimento;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalSchema hospital;

    public EntregaSchema(Entrega entrega) {
        this.id = entrega.getId();
        this.insumo = entrega.getInsumo().stream()
                .map(InsumoSchema::new)
                .collect(Collectors.toList());
        this.quantidade = entrega.getQuantidade();
        this.colaboradorRecebedor = new ColaboradorSchema(entrega.getColaboradorRecebedor());
        this.dataHoraRecebimento = entrega.getDataHoraRecebimento();
        this.hospital = new HospitalSchema(entrega.getHospital());
    }

    public Entrega toEntrega() {
        Entrega entrega = new Entrega(
                this.insumo.stream().map(InsumoSchema::toInsumo).collect(Collectors.toList()),
                this.quantidade,
                this.colaboradorRecebedor.toColaborador(),
                this.dataHoraRecebimento,
                this.hospital.toHospital()
        );
        entrega.setId(this.id);
        return entrega;
    }
}