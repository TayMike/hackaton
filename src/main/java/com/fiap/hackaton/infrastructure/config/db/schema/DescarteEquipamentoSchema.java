package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;
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
@Table(name = "descarte_equipamento")
public class DescarteEquipamentoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToMany
    @JoinTable(
            name = "Descarte_Equipamento_Itens",
            joinColumns = @JoinColumn(name = "descarte_id"),
            inverseJoinColumns = @JoinColumn(name = "equipamento_id")
    )
    private List<EquipamentoSchema> equipamentos;

    @ElementCollection
    @CollectionTable(
            name = "Descarte_Equipamento_Quantidade",
            joinColumns = @JoinColumn(name = "descarte_id")
    )
    @Column(name = "quantidade", nullable = false)
    private List<Long> quantidade;

    @ManyToOne
    @JoinColumn(name = "colaborador_responsavel_id", nullable = false)
    private ColaboradorSchema colaboradorResponsavel;

    @Column(name = "data_hora_descarte", nullable = false)
    private LocalDateTime dataHoraDescarte;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalSchema hospital;

    public DescarteEquipamentoSchema(DescarteEquipamento descarteEquipamento) {
        this.equipamentos = descarteEquipamento.getEquipamentos().stream()
                .map(EquipamentoSchema::new)
                .collect(Collectors.toList());
        this.quantidade = descarteEquipamento.getQuantidade();
        this.colaboradorResponsavel = new ColaboradorSchema(descarteEquipamento.getColaboradorResponsavel());
        this.dataHoraDescarte = descarteEquipamento.getDataHoraDescarte();
        this.hospital = new HospitalSchema(descarteEquipamento.getHospital());
    }

    public DescarteEquipamento toDescarteEquipamento() {
        DescarteEquipamento descarteEquipamento = new DescarteEquipamento(
                this.equipamentos.stream()
                        .map(EquipamentoSchema::toEquipamento)
                        .collect(Collectors.toList()),
                this.quantidade,
                this.colaboradorResponsavel.toColaborador(),
                this.dataHoraDescarte,
                this.hospital.toHospital()
        );
        descarteEquipamento.setId(this.id);
        return descarteEquipamento;
    }
}