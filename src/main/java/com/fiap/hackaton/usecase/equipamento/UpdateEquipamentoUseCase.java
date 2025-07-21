package com.fiap.hackaton.usecase.equipamento;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.equipamento.dto.IEquipamentoUpdateData;

import java.math.BigDecimal;
import java.util.UUID;

public class UpdateEquipamentoUseCase {

    private final EquipamentoGateway equipamentoGateway;
    private final HospitalGateway hospitalGateway;

    public UpdateEquipamentoUseCase(EquipamentoGateway equipamentoGateway, HospitalGateway hospitalGateway) {
        this.equipamentoGateway = equipamentoGateway;
        this.hospitalGateway = hospitalGateway;
    }

    public Equipamento execute(UUID id, IEquipamentoUpdateData dados) throws EquipamentoNotFoundException, ColaboradorNotFoundException, HospitalNotFoundException {
        Equipamento equipamento = this.equipamentoGateway.findById(id)
                .orElseThrow(EquipamentoNotFoundException::new).toEquipamento();

        HospitalSchema hospitalSchema = this.hospitalGateway.findById(id)
                .orElseThrow(HospitalNotFoundException::new);

        if (dados.nome() != null && !dados.nome().isBlank())
            equipamento.setNome(dados.nome());

        if (dados.custo() != null && !dados.custo().equals(new BigDecimal(0)))
            equipamento.setCusto(dados.custo());

        if (dados.tempoGarantia() != null)
            equipamento.setTempoGarantia(dados.tempoGarantia());

        if (dados.proximaManutencaoPreventiva() != null)
            equipamento.setProximaManutencaoPreventiva(dados.proximaManutencaoPreventiva());

        if (dados.marca() != null && !dados.marca().isBlank())
            equipamento.setMarca(dados.marca());

        if (dados.hospital() != null)
            equipamento.setHospital(hospitalSchema.toHospital());

        return this.equipamentoGateway.update(equipamento, hospitalSchema).toEquipamento();
    }

}