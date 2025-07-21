package com.fiap.hackaton.usecase.entregaInsumo;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.entregaInsumo.gateway.EntregaInsumoGateway;
import com.fiap.hackaton.entity.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.InsumoSchema;
import com.fiap.hackaton.usecase.entregaInsumo.dto.IEntregaInsumoRegistrationData;

import java.util.List;

public class CreateEntregaInsumoUseCase {

    private final EntregaInsumoGateway entregaInsumoGateway;
    private final InsumoGateway insumoGateway;
    private final ColaboradorGateway colaboradorGateway;
    private final HospitalGateway hospitalGateway;

    public CreateEntregaInsumoUseCase(EntregaInsumoGateway entregaInsumoGateway, InsumoGateway insumoGateway, ColaboradorGateway colaboradorGateway, HospitalGateway hospitalGateway) {
        this.entregaInsumoGateway = entregaInsumoGateway;
        this.insumoGateway = insumoGateway;
        this.colaboradorGateway = colaboradorGateway;
        this.hospitalGateway = hospitalGateway;
    }

    public EntregaInsumo execute(IEntregaInsumoRegistrationData dados) throws ColaboradorNotFoundException, HospitalNotFoundException {

        List<InsumoSchema> insumoSchemaList = this.insumoGateway.findAll()
                .stream()
                .filter(insumo -> dados.insumo().contains(insumo.getId())).toList();
        List<Insumo> insumos = insumoSchemaList.stream()
                .map(InsumoSchema::toInsumo)
                .toList();

        ColaboradorSchema colaboradorSchema = this.colaboradorGateway.findById(dados.colaboradorRecebedor())
                .orElseThrow(ColaboradorNotFoundException::new);

        HospitalSchema hospitalSchema = this.hospitalGateway.findById(dados.hospital())
                .orElseThrow(HospitalNotFoundException::new);

        EntregaInsumo entregaInsumo = new EntregaInsumo(insumos, dados.quantidade(), colaboradorSchema.toColaborador(),
                dados.dataHoraRecebimento(), hospitalSchema.toHospital());

        return this.entregaInsumoGateway.save(entregaInsumo, insumoSchemaList, colaboradorSchema, hospitalSchema);
    }

}
