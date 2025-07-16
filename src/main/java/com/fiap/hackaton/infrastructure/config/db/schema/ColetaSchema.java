package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fiap.hackaton.entity.coleta.model.Coleta;
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
@Table(name = "Coleta")
public class ColetaSchema extends AbstractEntitySchema<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToMany
    @JoinTable(
            name = "coleta_insumo",
            joinColumns = @JoinColumn(name = "coleta_id"),
            inverseJoinColumns = @JoinColumn(name = "insumo_id")
    )
    private List<InsumoSchema> insumos;

    @ElementCollection
    @CollectionTable(name = "coleta_quantidade", joinColumns = @JoinColumn(name = "coleta_id"))
    @Column(name = "quantidade", nullable = false)
    private List<Long> quantidades;

    @ManyToOne
    @JoinColumn(name = "colaborador_id", nullable = false)
    private ColaboradorSchema colaboradorEntregador;

    @Column(name = "data_hora_coleta", nullable = false)
    private LocalDateTime dataHoraColeta;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private PacienteSchema pacienteRecebedor;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalSchema hospital;

    public ColetaSchema(Coleta coleta) {
        this.insumos = coleta.getInsumos().stream()
                .map(InsumoSchema::new)
                .collect(Collectors.toList());

        this.quantidades = coleta.getQuantidades();
        this.colaboradorEntregador = new ColaboradorSchema(coleta.getColaboradorEntregador());
        this.dataHoraColeta = coleta.getDataHoraColeta();
        this.pacienteRecebedor = new PacienteSchema(coleta.getPacienteRecebedor());
        this.hospital = new HospitalSchema(coleta.getHospital());
    }

    public Coleta toColeta() {
        Coleta coleta = new Coleta(
                this.insumos.stream().map(InsumoSchema::toInsumo).collect(Collectors.toList()),
                this.quantidades,
                this.colaboradorEntregador.toColaborador(),
                this.dataHoraColeta,
                this.pacienteRecebedor.toPaciente(),
                this.hospital.toHospital()
        );
        coleta.setId(this.id);
        return coleta;
    }
}