package com.fiap.hackaton.usecase.equipamentoDominio.equipamento;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.equipamento.dto.IEquipamentoRegistrationData;
import org.springframework.transaction.annotation.Transactional;

public class CreateEquipamentoUseCase {

    private final EquipamentoGateway equipamentoGateway;

    public CreateEquipamentoUseCase(EquipamentoGateway equipamentoGateway) {
        this.equipamentoGateway = equipamentoGateway;
    }

    @Transactional
    public Equipamento execute(IEquipamentoRegistrationData dados) throws HospitalNotFoundException {

        Equipamento equipamento = new Equipamento(dados.nome(), dados.custo(), dados.tempoGarantia(),
                dados.proximaManutencaoPreventiva(), dados.numeroSerie(), dados.marca(), dados.descarte());

        return this.equipamentoGateway.save(equipamento);
    }

}
