package com.fiap.hackaton.usecase.equipamento;

import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.equipamento.dto.IEquipamentoRegistrationData;

public class CreateEquipamentoUseCase {

    private final EquipamentoGateway equipamentoGateway;

    public CreateEquipamentoUseCase(EquipamentoGateway equipamentoGateway) {
        this.equipamentoGateway = equipamentoGateway;
    }

    public Equipamento execute(IEquipamentoRegistrationData dados) {

        Equipamento equipamento = new Equipamento(dados.nome(), dados.custo(), dados.tempoGarantia(),
                dados.proximaManutencaoPreventiva(), dados.marca(), dados.hospital());

        return this.equipamentoGateway.save(equipamento);
    }

}
