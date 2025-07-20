package com.fiap.hackaton.usecase.entregaEquipamento;

import com.fiap.hackaton.entity.entregaEquipamento.gateway.EntregaEquipamentoGateway;
import com.fiap.hackaton.entity.entregaEquipamento.model.EntregaEquipamento;
import com.fiap.hackaton.usecase.entregaEquipamento.dto.IEntregaEquipamentoRegistrationData;

public class CreateEntregaEquipamentoUseCase {

    private final EntregaEquipamentoGateway entregaEquipamentoGateway;

    public CreateEntregaEquipamentoUseCase(EntregaEquipamentoGateway entregaEquipamentoGateway) {
        this.entregaEquipamentoGateway = entregaEquipamentoGateway;
    }

    public EntregaEquipamento execute(IEntregaEquipamentoRegistrationData dados) {

        EntregaEquipamento entregaEquipamento = new EntregaEquipamento(dados.equipamentos(), dados.quantidade(), dados.colaboradorRecebedor(),
                dados.dataHoraRecebimento(), dados.hospital());

        return this.entregaEquipamentoGateway.save(entregaEquipamento);
    }

}
