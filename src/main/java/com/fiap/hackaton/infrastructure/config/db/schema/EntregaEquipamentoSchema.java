package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fiap.hackaton.entity.entregaEquipamento.model.EntregaEquipamento;
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
@Table(name = "entrega_equipamento")
public class EntregaEquipamentoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToMany
    @JoinTable(
            name = "Entrega_Equipamento_Itens",
            joinColumns = @JoinColumn(name = "entrega_id"),
            inverseJoinColumns = @JoinColumn(name = "equipamento_id")
    )
    private List<EquipamentoSchema> equipamento;

    @ElementCollection
    @CollectionTable(
            name = "Entrega_Equipamento_Quantidade",
            joinColumns = @JoinColumn(name = "entrega_id")
    )
    @Column(name = "quantidade", nullable = false)
    private List<Long> quantidade;

    @ManyToOne
    @JoinColumn(name = "colaborador_recebedor_id", nullable = false)
    private ColaboradorSchema colaboradorRecebedor;

    @Column(name = "data_hora_recebimento", nullable = false)
    private LocalDateTime dataHoraRecebimento;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalSchema hospital;

    public EntregaEquipamentoSchema(EntregaEquipamento entregaEquipamento) {
        this.equipamento = entregaEquipamento.getEquipamentos()
                .stream()
                .map(EquipamentoSchema::new)
                .collect(Collectors.toList());
        this.quantidade = entregaEquipamento.getQuantidade();
        this.colaboradorRecebedor = new ColaboradorSchema(entregaEquipamento.getColaboradorRecebedor());
        this.dataHoraRecebimento = entregaEquipamento.getDataHoraRecebimento();
        this.hospital = new HospitalSchema(entregaEquipamento.getHospital());
    }

    public EntregaEquipamento toEntregaEquipamento() {
        EntregaEquipamento entregaEquipamento = new EntregaEquipamento(
                this.equipamento.stream()
                        .map(EquipamentoSchema::toEquipamento)
                        .collect(Collectors.toList()),
                this.quantidade,
                this.colaboradorRecebedor.toColaborador(),
                this.dataHoraRecebimento,
                this.hospital.toHospital()
        );
        entregaEquipamento.setId(this.id);
        return entregaEquipamento;
    }
}