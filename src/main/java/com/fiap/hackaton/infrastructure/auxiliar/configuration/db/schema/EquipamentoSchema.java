package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import jakarta.persistence.*;
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
@Table(name = "equipamento")
public class EquipamentoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "custo", nullable = false)
    private BigDecimal custo;

    @Column(name = "tempo_garantia", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private OffsetDateTime tempoGarantia;

    @Column(name = "proxima_manutencao_preventiva", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private OffsetDateTime proximaManutencaoPreventiva;

    @Column(name = "numero_serie", nullable = false)
    private String numeroSerie;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "descarte")
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