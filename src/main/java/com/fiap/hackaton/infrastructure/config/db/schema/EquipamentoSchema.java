package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fiap.hackaton.entity.equipamento.model.Equipamento;
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
    private OffsetDateTime tempoGarantia;

    @Column(name = "proxima_manutencao_preventiva", nullable = false)
    private OffsetDateTime proximaManutencaoPreventiva;

    @Column(name = "marca", nullable = false)
    private String marca;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalSchema hospital;

    public EquipamentoSchema(Equipamento equipamento , HospitalSchema hospitalSchema) {
        this.id = equipamento.getId();
        this.nome = equipamento.getNome();
        this.custo = equipamento.getCusto();
        this.tempoGarantia = equipamento.getTempoGarantia();
        this.proximaManutencaoPreventiva = equipamento.getProximaManutencaoPreventiva();
        this.marca = equipamento.getMarca();
        this.hospital = hospitalSchema;
    }

    public Equipamento toEquipamento() {
        Equipamento equipamento = new Equipamento(
                this.nome,
                this.custo,
                this.tempoGarantia,
                this.proximaManutencaoPreventiva,
                this.marca,
                this.hospital.toHospital()
        );
        equipamento.setId(this.getId());
        return equipamento;
    }
}