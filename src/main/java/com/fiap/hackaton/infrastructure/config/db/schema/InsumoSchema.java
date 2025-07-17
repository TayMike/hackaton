package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.entity.insumo.model.Insumo.Medida;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Insumo")
public class InsumoSchema extends AbstractEntitySchema<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal custo;

    @Column(nullable = false)
    private Long quantidade;

    @Column(nullable = false)
    private Long peso;

    @Column(nullable = false)
    private LocalDate validade;

    @Column(nullable = false)
    private String marca;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_medida", nullable = false)
    private Medida unidadeMedida;

    public InsumoSchema(Insumo insumo) {
        this.id = insumo.getId();
        this.nome = insumo.getNome();
        this.custo = insumo.getCusto();
        this.quantidade = insumo.getQuantidade();
        this.peso = insumo.getPeso();
        this.validade = insumo.getValidade();
        this.marca = insumo.getMarca();
        this.unidadeMedida = insumo.getUnidadeMedida();
    }

    public Insumo toInsumo() {
        Insumo insumo = new Insumo(
                this.nome,
                this.custo,
                this.quantidade,
                this.peso,
                this.validade,
                this.marca,
                this.unidadeMedida
        );

        insumo.setId(this.getId());
        return insumo;
    }
}
