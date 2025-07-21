package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "estoque_equipamento")
public class EstoqueEquipamentoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToMany
    @JoinTable(
            name = "Estoque_Equipamento_Itens",
            joinColumns = @JoinColumn(name = "estoque_id"),
            inverseJoinColumns = @JoinColumn(name = "equipamento_id")
    )
    private List<EquipamentoSchema> itens;

    @ElementCollection
    @CollectionTable(
            name = "Estoque_Equipamento_Quantidades",
            joinColumns = @JoinColumn(name = "estoque_id")
    )
    @Column(name = "quantidade", nullable = false)
    private List<Long> quantidades;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalSchema hospital;

    public EstoqueEquipamentoSchema(EstoqueEquipamento estoqueEquipamento, List<EquipamentoSchema> equipamentos, HospitalSchema hospitalSchema) {
        this.id = estoqueEquipamento.getId();
        this.itens = equipamentos;
        this.quantidades = estoqueEquipamento.getQuantidades();
        this.hospital = hospitalSchema;
    }

    public EstoqueEquipamento toEstoqueEquipamento() {
        EstoqueEquipamento estoqueEquipamento = new EstoqueEquipamento(
                this.itens.stream()
                        .map(EquipamentoSchema::toEquipamento)
                        .collect(Collectors.toList()),
                this.quantidades,
                this.hospital.toHospital()
        );
        estoqueEquipamento.setId(this.id);
        return estoqueEquipamento;
    }
}