package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo;
import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo.Medida;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "insumo",
        indexes = {
                @Index(name = "idx_insumo_nome", columnList = "nome")
        }
)
public class InsumoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Nome não pode estar vazio")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "Custo não pode ser nulo")
    @Positive(message = "Custo deve ser maior que zero")
    private BigDecimal custo;

    @Column(nullable = false)
    @NotNull(message = "Quantidade não pode ser nula")
    @PositiveOrZero(message = "Quantidade deve ser zero ou positiva")
    private Long quantidade;

    @Column(nullable = false)
    @NotNull(message = "Peso não pode ser nulo")
    @PositiveOrZero(message = "Peso deve ser zero ou positivo")
    private Long peso;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @NotNull(message = "Validade não pode ser nula")
    @FutureOrPresent(message = "Validade deve ser presente ou futura")
    private OffsetDateTime validade;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Marca não pode estar vazia")
    @Size(max = 50, message = "Marca deve ter no máximo 50 caracteres")
    private String marca;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_medida", nullable = false)
    @NotNull(message = "Unidade de medida não pode ser nula")
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

    public Insumo toEntity() {
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
