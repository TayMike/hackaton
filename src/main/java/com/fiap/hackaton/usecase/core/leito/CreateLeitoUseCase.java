package com.fiap.hackaton.usecase.core.leito;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.entity.core.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.core.leito.model.Leito;
import com.fiap.hackaton.entity.core.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.core.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import com.fiap.hackaton.usecase.core.leito.dto.ILeitoRegistrationData;
import org.springframework.transaction.annotation.Transactional;

public class CreateLeitoUseCase {

    private final LeitoGateway leitoGateway;
    private final HospitalGateway hospitalGateway;
    private final PacienteGateway pacienteGateway;

    public CreateLeitoUseCase(LeitoGateway leitoGateway, HospitalGateway hospitalGateway, PacienteGateway pacienteGateway) {
        this.leitoGateway = leitoGateway;
        this.hospitalGateway = hospitalGateway;
        this.pacienteGateway = pacienteGateway;
    }

    @Transactional
    public Leito execute(ILeitoRegistrationData dados) throws HospitalNotFoundException, PacienteNotFoundException {

        Hospital hospital = this.hospitalGateway.findById(dados.hospitalId()).orElseThrow(() -> new HospitalNotFoundException("Hospital not found: " + dados.hospitalId()));

        Paciente paciente = this.pacienteGateway.findById(dados.pacienteId())
                .orElseThrow(() -> new PacienteNotFoundException("Paciente not found: " + dados.pacienteId()));

        Leito leito = new Leito(dados.identificacao(), dados.pavilhao(), dados.quarto(),
                dados.hospitalId(), dados.pacienteId());

        return this.leitoGateway.save(leito);
    }

}