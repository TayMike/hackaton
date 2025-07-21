package com.fiap.hackaton.usecase.entregaEquipamento;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.entregaEquipamento.gateway.EntregaEquipamentoGateway;
import com.fiap.hackaton.entity.entregaEquipamento.model.EntregaEquipamento;
import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.entregaEquipamento.dto.IEntregaEquipamentoRegistrationData;

import java.util.List;

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

    public EntregaEquipamento execute(IEntregaEquipamentoRegistrationData dados) throws ColaboradorNotFoundException, HospitalNotFoundException {

        List<EquipamentoSchema> equipamentoSchemaList = this.equipamentoGateway.findAll()
                .stream()
                .filter(equipamento -> dados.equipamentos().contains(equipamento.getId()))
                .toList();
        List<Equipamento> equipamentos = equipamentoSchemaList.stream()
                .map(EquipamentoSchema::toEquipamento)
                .toList();

        ColaboradorSchema colaboradorSchema = this.colaboradorGateway.findById(dados.colaboradorRecebedor())
                .orElseThrow(ColaboradorNotFoundException::new);

        HospitalSchema hospitalSchema = this.hospitalGateway.findById(dados.hospital())
                .orElseThrow(HospitalNotFoundException::new);

        EntregaEquipamento entregaEquipamento = new EntregaEquipamento(equipamentos, dados.quantidade(), colaboradorSchema.toColaborador(),
                dados.dataHoraRecebimento(), hospitalSchema.toHospital());

        return this.entregaEquipamentoGateway.save(entregaEquipamento, equipamentoSchemaList, colaboradorSchema, hospitalSchema);
    }

}
