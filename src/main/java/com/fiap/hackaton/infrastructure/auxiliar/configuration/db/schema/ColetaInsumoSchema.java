package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;
import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.embeddable.QuantidadeInsumoEmbeddable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name = "coleta_insumo",
        indexes = {
                @Index(name = "idx_coleta_colaborador_id", columnList = "colaborador_id"),
                @Index(name = "idx_coleta_paciente_id", columnList = "paciente_id"),
                @Index(name = "idx_coleta_hospital_id", columnList = "hospital_id")
        }
)
public class ColetaInsumoSchema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "coleta_insumo_itens", joinColumns = @JoinColumn(name = "coleta_insumo_id"))
    @NotEmpty(message = "A lista de insumos não pode estar vazia")
    private List<QuantidadeInsumoEmbeddable> insumos;

    @Column(name = "colaborador_id", nullable = false)
    @NotNull(message = "Colaborador ID não pode ser nulo")
    private UUID colaboradorEntregadorId;

    @Column(name = "data_hora_coleta_insumo", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @NotNull(message = "Data e hora da coleta não podem ser nulas")
    private OffsetDateTime dataHoraColeta;

    @Column(name = "paciente_id", nullable = false)
    @NotNull(message = "Paciente ID não pode ser nulo")
    private UUID pacienteRecebedorId;

    @Column(name = "hospital_id", nullable = false)
    @NotNull(message = "Hospital ID não pode ser nulo")
    private UUID hospitalId;

    public ColetaInsumoSchema(ColetaInsumo coletaInsumo) {
        this.id = coletaInsumo.getId();
        this.insumos = coletaInsumo.getInsumos().stream()
                .map(i -> new QuantidadeInsumoEmbeddable(i.getInsumoId(), i.getQuantidade()))
                .toList();
        this.colaboradorEntregadorId = coletaInsumo.getColaboradorEntregadorId();
        this.dataHoraColeta = coletaInsumo.getDataHoraColeta();
        this.pacienteRecebedorId = coletaInsumo.getPacienteRecebedorId();
        this.hospitalId = coletaInsumo.getHospitalId();
    }

    public ColetaInsumo toEntity() {
        List<QuantidadeInsumo> itens = this.insumos.stream()
                .map(i -> new QuantidadeInsumo(i.getInsumoId(), i.getQuantidade()))
                .toList();
        ColetaInsumo coleta = new ColetaInsumo(
                itens,
                colaboradorEntregadorId,
                dataHoraColeta,
                pacienteRecebedorId,
                hospitalId
        );
        coleta.setId(this.id);
        return coleta;
    }
}