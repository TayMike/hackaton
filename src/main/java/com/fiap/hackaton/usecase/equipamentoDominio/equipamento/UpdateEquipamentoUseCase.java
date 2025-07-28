package com.fiap.hackaton.usecase.equipamentoDominio.equipamento;

import com.fiap.hackaton.entity.equipamentoDominio.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.equipamento.dto.IEquipamentoUpdateData;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

public class UpdateEquipamentoUseCase {

    private final EquipamentoGateway equipamentoGateway;

    public UpdateEquipamentoUseCase(EquipamentoGateway equipamentoGateway) {
        this.equipamentoGateway = equipamentoGateway;
    }

    @Transactional
    public Equipamento execute(UUID id, IEquipamentoUpdateData dados) throws EquipamentoNotFoundException {
        Equipamento equipamento = this.equipamentoGateway.findById(id)
                .orElseThrow(() -> new EquipamentoNotFoundException("Equipamento not found: " + id));

        if (dados.nome() != null && !dados.nome().isBlank())
            equipamento.setNome(dados.nome());

        if (dados.custo() != null && dados.custo().compareTo(new BigDecimal(0)) > 0)
            equipamento.setCusto(dados.custo());

        if (dados.tempoGarantia() != null)
            equipamento.setTempoGarantia(dados.tempoGarantia());

        if (dados.proximaManutencaoPreventiva() != null)
            equipamento.setProximaManutencaoPreventiva(dados.proximaManutencaoPreventiva());

        if (dados.marca() != null && !dados.marca().isBlank())
            equipamento.setMarca(dados.marca());

        if (dados.descarte() != null)
            equipamento.setDescarte(dados.descarte());

        return this.equipamentoGateway.update(equipamento);
    }

}