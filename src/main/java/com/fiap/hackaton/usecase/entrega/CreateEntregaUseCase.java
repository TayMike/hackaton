package com.fiap.hackaton.usecase.entrega;

import com.fiap.hackaton.entity.entrega.gateway.EntregaGateway;
import com.fiap.hackaton.entity.entrega.model.Entrega;
import com.fiap.hackaton.usecase.entrega.dto.IEntregaRegistrationData;

public class CreateEntregaUseCase {

    private final EntregaGateway entregaGateway;

    public CreateEntregaUseCase(EntregaGateway entregaGateway) {
        this.entregaGateway = entregaGateway;
    }

    public Entrega execute(IEntregaRegistrationData dados) {

        Entrega entrega = new Entrega(dados.insumo(), dados.quantidade(), dados.colaboradorRecebedor(),
                dados.dataHoraRecebimento(), dados.hospital());

        return this.entregaGateway.save(entrega);
    }

}
