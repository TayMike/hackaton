package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema;

import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.embeddable.QuantidadeInsumoEmbeddable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "estoque_insumo")
public class EstoqueInsumoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "estoque_quantidade_insumo", joinColumns = @JoinColumn(name = "estoque_id"))
    private List<QuantidadeInsumoEmbeddable> insumos;

    @Column(name = "hospital_id", nullable = false)
    private UUID hospitalId;

    public EstoqueInsumoSchema(EstoqueInsumo estoqueInsumo) {
        this.id = estoqueInsumo.getId();
        this.insumos = estoqueInsumo.getInsumos().stream().map(i -> new QuantidadeInsumoEmbeddable(i.getInsumoId(), i.getQuantidade())).toList();
        this.hospitalId = estoqueInsumo.getHospitalId();
    }

    public EstoqueInsumo toEntity() {
            EstoqueInsumo estoque = new EstoqueInsumo();
            estoque.setId(id);
            estoque.setInsumos(
                    insumos.stream()
                            .map(i -> new QuantidadeInsumo(i.getInsumoId(), i.getQuantidade()))
                            .toList());
            estoque.setHospitalId(hospitalId);
            return estoque;
        }
}