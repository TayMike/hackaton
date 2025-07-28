package com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento;

import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento.dto.IEstoqueEquipamentoRegistrationData;
import org.springframework.transaction.annotation.Transactional;

public class CreateEstoqueEquipamentoUseCase {

    private final EstoqueEquipamentoGateway estoqueGateway;
    private final EquipamentoGateway equipamentoGateway;
    private final ColaboradorGateway colaboradorGateway;
    private final HospitalGateway hospitalGateway;

    public CreateEstoqueEquipamentoUseCase(EstoqueEquipamentoGateway estoqueGateway, EquipamentoGateway equipamentoGateway, ColaboradorGateway colaboradorGateway, HospitalGateway hospitalGateway) {
        this.estoqueGateway = estoqueGateway;
        this.equipamentoGateway = equipamentoGateway;
        this.colaboradorGateway = colaboradorGateway;
        this.hospitalGateway = hospitalGateway;
    }

    @Transactional
    public EstoqueEquipamento execute(IEstoqueEquipamentoRegistrationData dados) throws HospitalNotFoundException, EquipamentoNotFoundException {

        Equipamento equipamento = this.equipamentoGateway.findById(dados.equipamentoId()).orElseThrow(() -> new EquipamentoNotFoundException("Equipamento not found: " + dados.equipamentoId()));

        if (dados.colaboradorEntregadorId() != null) {
            Colaborador colaboradorEntregador = this.colaboradorGateway.findById(dados.colaboradorEntregadorId())
                    .orElseThrow(() -> new EquipamentoNotFoundException("Colaborador not found: " + dados.colaboradorEntregadorId()));
        }

        if (dados.colaboradorResponsavelId() != null) {
            Colaborador colaboradorResponsavel = this.colaboradorGateway.findById(dados.colaboradorResponsavelId())
                    .orElseThrow(() -> new EquipamentoNotFoundException("Colaborador not found: " + dados.colaboradorResponsavelId()));
        }

        Hospital hospital = this.hospitalGateway.findById(dados.hospitalId()).orElseThrow(() -> new HospitalNotFoundException("Hospital not found: " + dados.hospitalId()));

        EstoqueEquipamento estoqueEquipamento = new EstoqueEquipamento(dados.equipamentoId(), dados.hospitalId(), dados.colaboradorEntregadorId(), dados.dataHoraColeta(),
        dados.colaboradorResponsavelId());

        return this.estoqueGateway.save(estoqueEquipamento);
    }

}
