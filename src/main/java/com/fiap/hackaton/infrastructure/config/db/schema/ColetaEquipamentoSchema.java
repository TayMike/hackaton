package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Coleta_Equipamento")
public class ColetaEquipamentoSchema extends AbstractEntitySchema<UUID> {

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
    private LocalDateTime dataHoraColeta;

    @ManyToOne
    @JoinColumn(name = "colaborador_responsavel_id", nullable = false)
    private ColaboradorSchema colaboradorResponsavel;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalSchema hospital;

    public ColetaEquipamentoSchema(ColetaEquipamento coletaEquipamento) {
        this.equipamentos = coletaEquipamento.getEquipamentos()
                .stream()
                .map(EquipamentoSchema::new)
                .collect(Collectors.toList());
        this.quantidades = coletaEquipamento.getQuantidades();
        this.colaboradorEntregador = new ColaboradorSchema(coletaEquipamento.getColaboradorEntregador());
        this.dataHoraColeta = coletaEquipamento.getDataHoraColeta();
        this.colaboradorResponsavel = new ColaboradorSchema(coletaEquipamento.getColaboradorResponsavel());
        this.hospital = new HospitalSchema(coletaEquipamento.getHospital());
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
        coletaEquipamento.setId(this.getId());
        return coletaEquipamento;
    }
}