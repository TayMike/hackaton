package com.fiap.hackaton.usecase.equipamento;

import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.equipamento.dto.IEquipamentoRegistrationData;

public class CreateEquipamentoUseCase {

    private final EquipamentoGateway equipamentoGateway;
    private final HospitalGateway hospitalGateway;

    public CreateEquipamentoUseCase(EquipamentoGateway equipamentoGateway, HospitalGateway hospitalGateway) {
        this.equipamentoGateway = equipamentoGateway;
        this.hospitalGateway = hospitalGateway;
    }

    public Equipamento execute(IEquipamentoRegistrationData dados) throws HospitalNotFoundException {

        HospitalSchema hospitalSchema = hospitalGateway.findById(dados.hospital())
                .orElseThrow(HospitalNotFoundException::new);

        Equipamento equipamento = new Equipamento(dados.nome(), dados.custo(), dados.tempoGarantia(),
                dados.proximaManutencaoPreventiva(), dados.marca(), hospitalSchema.toHospital());

        return this.equipamentoGateway.save(equipamento, hospitalSchema).toEquipamento();
    }

}
