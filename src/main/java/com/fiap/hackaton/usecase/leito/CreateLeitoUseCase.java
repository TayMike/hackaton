package com.fiap.hackaton.usecase.leito;

import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.entity.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.PacienteSchema;
import com.fiap.hackaton.usecase.leito.dto.ILeitoRegistrationData;

public class CreateLeitoUseCase {

    private final LeitoGateway leitoGateway;
    private final HospitalGateway hospitalGateway;
    private final PacienteGateway pacienteGateway;

    public CreateLeitoUseCase(LeitoGateway leitoGateway, HospitalGateway hospitalGateway, PacienteGateway pacienteGateway) {
        this.leitoGateway = leitoGateway;
        this.hospitalGateway = hospitalGateway;
        this.pacienteGateway = pacienteGateway;
    }

    public Leito execute(ILeitoRegistrationData dados) throws HospitalNotFoundException, PacienteNotFoundException {

        HospitalSchema hospitalSchema = this.hospitalGateway.findById(dados.hospital())
                .orElseThrow(HospitalNotFoundException::new);

        PacienteSchema pacienteSchema = this.pacienteGateway.findById(dados.paciente())
                .orElseThrow(PacienteNotFoundException::new);

        Leito leito = new Leito(dados.identificacao(), dados.pavilhao(), dados.quarto(),
                hospitalSchema.toHospital(), pacienteSchema.toPaciente());

        return this.leitoGateway.save(leito, hospitalSchema, pacienteSchema).toLeito();
    }

}