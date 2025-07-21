package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "coleta_equipamento")
public class ColetaEquipamentoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToMany
    @JoinTable(
            name = "coleta_equipamento_itens",
            joinColumns = @JoinColumn(name = "coleta_equipamento_id"),
            inverseJoinColumns = @JoinColumn(name = "equipamento_id")
    )
    private List<EquipamentoSchema> equipamentos;

    @ElementCollection
    @CollectionTable(name = "coleta_equipamento_quantidade", joinColumns = @JoinColumn(name = "coleta_equipamento_id"))
    @Column(name = "quantidade")
    private List<Long> quantidades;

    @ManyToOne
    @JoinColumn(name = "colaborador_entregador_id", nullable = false)
    private ColaboradorSchema colaboradorEntregador;

    @Column(nullable = false, name = "data_hora_coleta_equipamento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private OffsetDateTime dataHoraColeta;

    @ManyToOne
    @JoinColumn(name = "colaborador_responsavel_id", nullable = false)
    private ColaboradorSchema colaboradorResponsavel;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalSchema hospital;

    public ColetaEquipamentoSchema(ColetaEquipamento coletaEquipamento, List<EquipamentoSchema> equipamentos, ColaboradorSchema colaboradorEntregador, ColaboradorSchema colaboradorResponsavel, HospitalSchema hospitalSchema) {
        this.id = coletaEquipamento.getId();
        this.equipamentos = equipamentos;
        this.quantidades = coletaEquipamento.getQuantidades();
        this.colaboradorEntregador = colaboradorEntregador;
        this.dataHoraColeta = coletaEquipamento.getDataHoraColeta();
        this.colaboradorResponsavel = colaboradorResponsavel;
        this.hospital = hospitalSchema;
    }

    public ColetaEquipamento toColetaEquipamento() {
        ColetaEquipamento coletaEquipamento = new ColetaEquipamento(
                this.equipamentos
                        .stream()
                        .map(EquipamentoSchema::toEquipamento)
                        .collect(Collectors.toList()),
                this.quantidades,
                this.colaboradorEntregador.toColaborador(),
                this.dataHoraColeta,
                this.colaboradorResponsavel.toColaborador(),
                this.hospital.toHospital()
        );
        coletaEquipamento.setId(this.id);
        return coletaEquipamento;
    }
}