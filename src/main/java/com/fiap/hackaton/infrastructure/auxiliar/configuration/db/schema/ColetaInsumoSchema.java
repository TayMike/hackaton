package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;
import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.embeddable.QuantidadeInsumoEmbeddable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "coleta_insumo")
@Getter
@Setter
@NoArgsConstructor
public class ColetaInsumoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "coleta_insumo_itens", joinColumns = @JoinColumn(name = "coleta_insumo_id"))
    private List<QuantidadeInsumoEmbeddable> insumos;

    @Column(name = "colaborador_id", nullable = false)
    private UUID colaboradorEntregadorId;

    @Column(name = "data_hora_coleta_insumo", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private OffsetDateTime dataHoraColeta;

    @Column(name = "paciente_id", nullable = false)
    private UUID pacienteRecebedorId;

    @Column(name = "hospital_id", nullable = false)
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