package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
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
@Table(name = "coleta_insumo")
public class ColetaInsumoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToMany
    @JoinTable(
            name = "coleta_insumo_itens",
            joinColumns = @JoinColumn(name = "coleta_insumo_id"),
            inverseJoinColumns = @JoinColumn(name = "insumo_id")
    )
    private List<InsumoSchema> insumos;

    @ElementCollection
    @CollectionTable(name = "coleta_insumo_quantidade", joinColumns = @JoinColumn(name = "coleta_insumo_id"))
    @Column(name = "quantidade", nullable = false)
    private List<Long> quantidades;

    @ManyToOne
    @JoinColumn(name = "colaborador_id", nullable = false)
    private ColaboradorSchema colaboradorEntregador;

    @Column(name = "data_hora_coleta_insumo", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private OffsetDateTime dataHoraColeta;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private PacienteSchema pacienteRecebedor;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalSchema hospital;

    public ColetaInsumoSchema(ColetaInsumo coletaInsumo, List<InsumoSchema> insumos, ColaboradorSchema colaboradorSchema, PacienteSchema pacienteSchema, HospitalSchema hospitalSchema) {
        this.id = coletaInsumo.getId();
        this.insumos = insumos;
        this.quantidades = coletaInsumo.getQuantidades();
        this.colaboradorEntregador = colaboradorSchema;
        this.dataHoraColeta = coletaInsumo.getDataHoraColeta();
        this.pacienteRecebedor = pacienteSchema;
        this.hospital = hospitalSchema;
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