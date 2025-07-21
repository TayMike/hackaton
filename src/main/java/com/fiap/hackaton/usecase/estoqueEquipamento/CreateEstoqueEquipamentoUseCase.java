package com.fiap.hackaton.usecase.estoqueEquipamento;

import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.estoqueEquipamento.dto.IEstoqueEquipamentoRegistrationData;

import java.util.List;

public class CreateEstoqueEquipamentoUseCase {

    private final EstoqueEquipamentoGateway estoqueGateway;
    private final EquipamentoGateway equipamentoGateway;
    private final HospitalGateway hospitalGateway;

    public CreateEstoqueEquipamentoUseCase(EstoqueEquipamentoGateway estoqueGateway, EquipamentoGateway equipamentoGateway, HospitalGateway hospitalGateway) {
        this.estoqueGateway = estoqueGateway;
        this.equipamentoGateway = equipamentoGateway;
        this.hospitalGateway = hospitalGateway;
    }

    public EstoqueEquipamento execute(IEstoqueEquipamentoRegistrationData dados) throws HospitalNotFoundException {

        List<EquipamentoSchema> equipamentoSchemaList = this.equipamentoGateway.findAll()
                .stream()
                .filter(equipamento -> dados.itens().contains(equipamento.getId()))
                .toList();

        List<Equipamento> equipamentos = equipamentoSchemaList.stream()
                .map(EquipamentoSchema::toEquipamento)
                .toList();

        HospitalSchema hospitalSchema = this.hospitalGateway.findById(dados.hospital())
                .orElseThrow(HospitalNotFoundException::new);

        EstoqueEquipamento estoqueEquipamento = new EstoqueEquipamento(equipamentos, dados.quantidades(), hospitalSchema.toHospital());

        return this.estoqueGateway.save(estoqueEquipamento, equipamentoSchemaList, hospitalSchema).toEstoqueEquipamento();
    }


}
