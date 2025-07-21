package com.fiap.hackaton.usecase.coletaInsumo;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.coletaInsumo.gateway.ColetaInsumoGateway;
import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.InsumoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.PacienteSchema;
import com.fiap.hackaton.usecase.coletaInsumo.dto.IColetaInsumoRegistrationData;

import java.util.List;

public class CreateColetaInsumoUseCase {

    private final ColetaInsumoGateway coletaInsumoGateway;
    private final ColaboradorGateway colaboradorGateway;
    private final PacienteGateway pacienteGateway;
    private final HospitalGateway hospitalGateway;
    private final InsumoGateway insumoGateway;

    public CreateColetaInsumoUseCase(ColetaInsumoGateway coletaInsumoGateway, ColaboradorGateway colaboradorGateway, PacienteGateway pacienteGateway, HospitalGateway hospitalGateway, InsumoGateway insumoGateway) {
        this.coletaInsumoGateway = coletaInsumoGateway;
        this.colaboradorGateway = colaboradorGateway;
        this.pacienteGateway = pacienteGateway;
        this.hospitalGateway = hospitalGateway;
        this.insumoGateway = insumoGateway;
    }

    public ColetaInsumo execute(IColetaInsumoRegistrationData dados) throws ColaboradorNotFoundException, PacienteNotFoundException, HospitalNotFoundException {

        ColaboradorSchema colaboradorSchema = colaboradorGateway.findById(dados.colaboradorEntregador())
                .orElseThrow(ColaboradorNotFoundException::new);

        PacienteSchema pacienteSchema = pacienteGateway.findById(dados.pacienteRecebedor())
                .orElseThrow(PacienteNotFoundException::new);

        HospitalSchema hospitalSchema = hospitalGateway.findById(dados.hospital())
                .orElseThrow(HospitalNotFoundException::new);

        List<InsumoSchema> insumos = insumoGateway.findAll().stream().filter(insumo -> dados.insumos().contains(insumo.getId())).toList();

        ColetaInsumo coletaInsumo = new ColetaInsumo(insumos.stream().map(InsumoSchema::toInsumo).toList(), dados.quantidades(), colaboradorSchema.toColaborador(),
                dados.dataHoraColeta(), pacienteSchema.toPaciente(), hospitalSchema.toHospital());

        return this.coletaInsumoGateway.save(coletaInsumo, insumos, colaboradorSchema, pacienteSchema, hospitalSchema);
    }

}