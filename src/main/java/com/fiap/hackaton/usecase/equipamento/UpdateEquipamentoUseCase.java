package com.fiap.hackaton.usecase.equipamento;

import com.fiap.hackaton.entity.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.equipamento.dto.IEquipamentoUpdateData;

import java.math.BigDecimal;
import java.util.UUID;

public class UpdateEquipamentoUseCase {

    private final EquipamentoGateway equipamentoGateway;

    public UpdateEquipamentoUseCase(EquipamentoGateway equipamentoGateway) {
        this.equipamentoGateway = equipamentoGateway;
    }

    public Equipamento execute(UUID id, IEquipamentoUpdateData dados) throws EquipamentoNotFoundException {
        Equipamento equipamento = this.equipamentoGateway.findById(id)
                .orElseThrow(EquipamentoNotFoundException::new);

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
            equipamento.setHospital(dados.hospital());

        return this.equipamentoGateway.update(equipamento);
    }

}