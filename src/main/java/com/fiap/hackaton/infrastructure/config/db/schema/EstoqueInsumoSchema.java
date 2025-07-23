package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "estoque_insumo")
public class EstoqueInsumoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "estoque_insumo_itens",
            joinColumns = @JoinColumn(name = "estoque_id"),
            inverseJoinColumns = @JoinColumn(name = "insumo_id")
    )
    private List<InsumoSchema> itens = new ArrayList<>();


    @ElementCollection
    @CollectionTable(name = "estoque_quantidade", joinColumns = @JoinColumn(name = "estoque_id"))
    @Column(name = "quantidade", nullable = false)
    private List<Long> quantidades = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalSchema hospital;

    public EstoqueInsumoSchema(EstoqueInsumo estoqueInsumo, List<InsumoSchema> itens, HospitalSchema hospitalSchema) {
        this.id = estoqueInsumo.getId();
        this.itens = itens;
        this.quantidades = estoqueInsumo.getQuantidades();
        this.hospital = hospitalSchema;
    }

    public EstoqueInsumo toEstoqueInsumo() {
        EstoqueInsumo estoqueInsumo = new EstoqueInsumo(
                this.itens.stream().map(InsumoSchema::toInsumo).collect(Collectors.toList()),
                this.quantidades,
                this.hospital.toHospital()
        );
        estoqueInsumo.setId(this.id);
        return estoqueInsumo;
    }
}
