package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "equipamento",
        indexes = {
                @Index(name = "idx_equipamento_numero_serie", columnList = "numero_serie"),
                @Index(name = "idx_equipamento_marca", columnList = "marca")
        }
)
public class EquipamentoSchema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 100)
    @NotBlank(message = "Nome não pode estar vazio")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nome;

    @Column(name = "custo", nullable = false)
    @NotNull(message = "Custo não pode ser nulo")
    @Positive(message = "Custo deve ser maior que zero")
    private BigDecimal custo;

    @Column(name = "tempo_garantia", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @NotNull(message = "Tempo de garantia não pode ser nulo")
    private OffsetDateTime tempoGarantia;

    @Column(name = "proxima_manutencao_preventiva", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @NotNull(message = "Próxima manutenção preventiva não pode ser nula")
    private OffsetDateTime proximaManutencaoPreventiva;

    @Column(name = "numero_serie", nullable = false, unique = true, length = 50)
    @NotBlank(message = "Número de série não pode estar vazio")
    @Size(max = 50, message = "Número de série deve ter no máximo 50 caracteres")
    private String numeroSerie;

    @Column(name = "marca", nullable = false, length = 50)
    @NotBlank(message = "Marca não pode estar vazia")
    @Size(max = 50, message = "Marca deve ter no máximo 50 caracteres")
    private String marca;

    @Column(name = "descarte")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime descarte;

    public EquipamentoSchema(Equipamento equipamento) {
        this.id = equipamento.getId();
        this.nome = equipamento.getNome();
        this.custo = equipamento.getCusto();
        this.tempoGarantia = equipamento.getTempoGarantia();
        this.proximaManutencaoPreventiva = equipamento.getProximaManutencaoPreventiva();
        this.numeroSerie = equipamento.getNumeroSerie();
        this.marca = equipamento.getMarca();
        this.descarte = equipamento.getDescarte();
    }

    public Equipamento toEntity() {
        Equipamento equipamento = new Equipamento(
                this.nome,
                this.custo,
                this.tempoGarantia,
                this.proximaManutencaoPreventiva,
                this.numeroSerie,
                this.marca,
                this.descarte
        );
        equipamento.setId(this.getId());
        return equipamento;
    }
}