package com.fiap.hackaton.usecase.coletaEquipamento;

import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.coletaEquipamento.gateway.ColetaEquipamentoGateway;
import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;
import com.fiap.hackaton.entity.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.coletaEquipamento.dto.IColetaEquipamentoRegistrationData;

import java.util.List;

public class CreateColetaEquipamentoUseCase {

    private final ColetaEquipamentoGateway coletaEquipamentoGateway;
    private final EquipamentoGateway equipamentoGateway;
    private final ColaboradorGateway colaboradorGateway;
    private final HospitalGateway hospitalGateway;

    public CreateColetaEquipamentoUseCase(ColetaEquipamentoGateway coletaEquipamentoGateway, EquipamentoGateway equipamentoGateway, ColaboradorGateway colaboradorGateway, HospitalGateway hospitalGateway) {
        this.coletaEquipamentoGateway = coletaEquipamentoGateway;
        this.equipamentoGateway = equipamentoGateway;
        this.colaboradorGateway = colaboradorGateway;
        this.hospitalGateway = hospitalGateway;
    }

    public ColetaEquipamento execute(IColetaEquipamentoRegistrationData dados) throws EquipamentoNotFoundException {

        List<EquipamentoSchema> equipamentoSchema = equipamentoGateway.findAll().stream().filter(equipamento -> dados.equipamentos().contains(equipamento.getId())).toList();
        List<Equipamento> equipamentos = equipamentoSchema.stream().map(EquipamentoSchema::toEquipamento).toList();

        ColaboradorSchema colaboradorSchema = colaboradorGateway.findById(dados.colaboradorEntregador())
                .orElseThrow(EquipamentoNotFoundException::new);

        ColaboradorSchema colaboradorResponsavelSchema = colaboradorGateway.findById(dados.colaboradorResponsavel())
                .orElseThrow(EquipamentoNotFoundException::new);

        HospitalSchema hospitalSchema = hospitalGateway.findById(dados.hospital())
                .orElseThrow(EquipamentoNotFoundException::new);

        ColetaEquipamento coletaEquipamento = new ColetaEquipamento(equipamentos, dados.quantidades(), colaboradorSchema.toColaborador(),
                dados.dataHoraColeta(), colaboradorResponsavelSchema.toColaborador(), hospitalSchema.toHospital());

        return this.coletaEquipamentoGateway.save(coletaEquipamento, equipamentoSchema, colaboradorSchema, colaboradorResponsavelSchema, hospitalSchema).toColetaEquipamento();
    }

}
