package com.fiap.hackaton.usecase.equipamentoDominio.entregaEquipamento;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.exception.EntregaEquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.gateway.EntregaEquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.model.EntregaEquipamento;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.entregaEquipamento.dto.IEntregaEquipamentoRegistrationData;
import org.springframework.transaction.annotation.Transactional;

public class CreateEntregaEquipamentoUseCase {

    private final EntregaEquipamentoGateway entregaEquipamentoGateway;
    private final EquipamentoGateway equipamentoGateway;
    private final ColaboradorGateway colaboradorGateway;
    private final HospitalGateway hospitalGateway;

    public CreateEntregaEquipamentoUseCase(EntregaEquipamentoGateway entregaEquipamentoGateway, EquipamentoGateway equipamentoGateway, ColaboradorGateway colaboradorGateway, HospitalGateway hospitalGateway) {
        this.entregaEquipamentoGateway = entregaEquipamentoGateway;
        this.equipamentoGateway = equipamentoGateway;
        this.colaboradorGateway = colaboradorGateway;
        this.hospitalGateway = hospitalGateway;
    }

    @Transactional
    public EntregaEquipamento execute(IEntregaEquipamentoRegistrationData dados) throws ColaboradorNotFoundException, HospitalNotFoundException, EntregaEquipamentoNotFoundException {

        Equipamento equipamento = this.equipamentoGateway.findById(dados.equipamentoId()).orElseThrow(() -> new EntregaEquipamentoNotFoundException("Equipamento not found: " + dados.equipamentoId()));

        Colaborador colaborador = this.colaboradorGateway.findById(dados.colaboradorRecebedorId())
                .orElseThrow(() -> new ColaboradorNotFoundException("Colaborador not found: " + dados.hospitalId()));

        Hospital hospital = this.hospitalGateway.findById(dados.hospitalId())
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found: " + dados.hospitalId()));

        EntregaEquipamento entregaEquipamento = new EntregaEquipamento(dados.equipamentoId(), dados.colaboradorRecebedorId(),
                dados.dataHoraRecebimento(), dados.hospitalId());

        return this.entregaEquipamentoGateway.save(entregaEquipamento);
    }

}
