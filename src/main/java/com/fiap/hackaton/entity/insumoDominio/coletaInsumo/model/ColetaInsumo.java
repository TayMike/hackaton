package com.fiap.hackaton.entity.insumoDominio.coletaInsumo.model;

import com.fiap.hackaton.entity.auxiliar.AbstractEntity;
import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class ColetaInsumo extends AbstractEntity implements Serializable {

    @NonNull
    private List<QuantidadeInsumo> insumos;
    @NonNull
    private UUID colaboradorEntregadorId;
    @NonNull
    private OffsetDateTime dataHoraColeta;
    @NonNull
    private UUID pacienteRecebedorId;
    @NonNull
    private UUID hospitalId;

        public ColetaInsumo(@NonNull List<QuantidadeInsumo> insumos, @NonNull UUID colaboradorEntregadorId, @NonNull OffsetDateTime dataHoraColeta, @NonNull UUID pacienteRecebedorId, @NonNull UUID hospitalId) {
        this.insumos = insumos;
        this.colaboradorEntregadorId = colaboradorEntregadorId;
        this.dataHoraColeta = dataHoraColeta;
        this.pacienteRecebedorId = pacienteRecebedorId;
        this.hospitalId = hospitalId;
    }
}
