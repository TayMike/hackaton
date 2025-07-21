package com.fiap.hackaton.usecase.descarteEquipamento;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.descarteEquipamento.gateway.DescarteEquipamentoGateway;
import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;
import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.descarteEquipamento.dto.IDescarteEquipamentoRegistrationData;

import java.util.List;

public class CreateDescarteEquipamentoUseCase {

    private final DescarteEquipamentoGateway descarteEquipamentoGateway;
    private final EquipamentoGateway equipamentoGateway;
    private final ColaboradorGateway colaboradorGateway;
    private final HospitalGateway hospitalGateway;

    public CreateDescarteEquipamentoUseCase(DescarteEquipamentoGateway descarteEquipamentoGateway, EquipamentoGateway equipamentoGateway, ColaboradorGateway colaboradorGateway, HospitalGateway hospitalGateway) {
        this.descarteEquipamentoGateway = descarteEquipamentoGateway;
        this.equipamentoGateway = equipamentoGateway;
        this.colaboradorGateway = colaboradorGateway;
        this.hospitalGateway = hospitalGateway;
    }

    public DescarteEquipamento execute(IDescarteEquipamentoRegistrationData dados) throws ColaboradorNotFoundException, HospitalNotFoundException {

        List<EquipamentoSchema> equipamentoSchemaList = this.equipamentoGateway.findAll()
                .stream()
                .filter(equipamento -> dados.equipamentos().contains(equipamento.getId()))
                .toList();
        List<Equipamento> equipamentos = equipamentoSchemaList.stream()
                .map(EquipamentoSchema::toEquipamento)
                .toList();

        ColaboradorSchema colaboradorSchema = this.colaboradorGateway.findById(dados.colaboradorResponsavel())
                .orElseThrow(ColaboradorNotFoundException::new);

        HospitalSchema hospitalSchema = this.hospitalGateway.findById(dados.hospital())
                .orElseThrow(HospitalNotFoundException::new);

        DescarteEquipamento descarteEquipamento = new DescarteEquipamento(equipamentos, dados.quantidade(), colaboradorSchema.toColaborador(),
                dados.dataHoraDescarte(), hospitalSchema.toHospital());

        return this.descarteEquipamentoGateway.save(descarteEquipamento, equipamentoSchemaList, colaboradorSchema, hospitalSchema);
    }

}