package com.fiap.hackaton.usecase.entregaInsumo;

import com.fiap.hackaton.entity.entregaInsumo.gateway.EntregaInsumoGateway;
import com.fiap.hackaton.entity.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.usecase.entregaInsumo.dto.IEntregaInsumoRegistrationData;

public class CreateEntregaInsumoUseCase {

    private final EntregaInsumoGateway entregaInsumoGateway;

    public CreateEntregaInsumoUseCase(EntregaInsumoGateway entregaInsumoGateway) {
        this.entregaInsumoGateway = entregaInsumoGateway;
    }

    public EntregaInsumo execute(IEntregaInsumoRegistrationData dados) {

        EntregaInsumo entregaInsumo = new EntregaInsumo(dados.insumo(), dados.quantidade(), dados.colaboradorRecebedor(),
                dados.dataHoraRecebimento(), dados.hospital());

        return this.entregaInsumoGateway.save(entregaInsumo);
    }

}
