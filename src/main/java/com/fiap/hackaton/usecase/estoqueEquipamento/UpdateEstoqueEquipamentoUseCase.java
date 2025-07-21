package com.fiap.hackaton.usecase.estoqueEquipamento;

import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.entity.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.estoqueEquipamento.dto.IEstoqueEquipamentoUpdateData;

import java.util.List;
import java.util.UUID;

public class UpdateEstoqueEquipamentoUseCase {

    private final EstoqueEquipamentoGateway estoqueGateway;
    private final EquipamentoGateway equipamentoGateway;
    private final HospitalGateway hospitalGateway;

    public UpdateEstoqueEquipamentoUseCase(EstoqueEquipamentoGateway estoqueGateway, EquipamentoGateway equipamentoGateway, HospitalGateway hospitalGateway) {
        this.estoqueGateway = estoqueGateway;
        this.equipamentoGateway = equipamentoGateway;
        this.hospitalGateway = hospitalGateway;
    }

    public EstoqueEquipamento execute(UUID id, IEstoqueEquipamentoUpdateData dados) throws EstoqueEquipamentoNotFoundException, HospitalNotFoundException {
        EstoqueEquipamento estoqueEquipamento = this.estoqueGateway.findById(id)
                .orElseThrow(EstoqueEquipamentoNotFoundException::new).toEstoqueEquipamento();

        List<EquipamentoSchema> equipamentoSchemaList = this.equipamentoGateway.findAll()
                .stream()
                .filter(equipamento -> dados.itens().contains(dados.itens()))
                .toList();
        List<Equipamento> itens = equipamentoSchemaList.stream()
                .map(EquipamentoSchema::toEquipamento)
                .toList();

        HospitalSchema hospitalSchema = this.hospitalGateway.findById(dados.hospital())
                .orElseThrow(HospitalNotFoundException::new);

        if (!itens.isEmpty())
            estoqueEquipamento.setItens(itens);

        if (dados.quantidades() != null && !dados.quantidades().isEmpty())
            estoqueEquipamento.setQuantidades(dados.quantidades());

        if (hospitalSchema != null)
            estoqueEquipamento.setHospital(hospitalSchema.toHospital());

        return this.estoqueGateway.update(estoqueEquipamento, equipamentoSchemaList, hospitalSchema).toEstoqueEquipamento();
    }

}
