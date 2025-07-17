package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
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
@Table(name = "Coleta_Insumo")
public class ColetaInsumoSchema extends AbstractEntitySchema<UUID> {

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

    public ColetaInsumoSchema(ColetaInsumo coletaInsumo) {
        this.insumos = coletaInsumo.getInsumos().stream()
                .map(InsumoSchema::new)
                .collect(Collectors.toList());

        this.quantidades = coletaInsumo.getQuantidades();
        this.colaboradorEntregador = new ColaboradorSchema(coletaInsumo.getColaboradorEntregador());
        this.dataHoraColeta = coletaInsumo.getDataHoraColeta();
        this.pacienteRecebedor = new PacienteSchema(coletaInsumo.getPacienteRecebedor());
        this.hospital = new HospitalSchema(coletaInsumo.getHospital());
    }

    public ColetaInsumo toColeta() {
        ColetaInsumo coletaInsumo = new ColetaInsumo(
                this.insumos.stream().map(InsumoSchema::toInsumo).collect(Collectors.toList()),
                this.quantidades,
                this.colaboradorEntregador.toColaborador(),
                this.dataHoraColeta,
                this.pacienteRecebedor.toPaciente(),
                this.hospital.toHospital()
        );
        coletaInsumo.setId(this.id);
        return coletaInsumo;
    }
}