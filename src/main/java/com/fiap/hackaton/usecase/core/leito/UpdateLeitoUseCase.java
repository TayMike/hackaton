package com.fiap.hackaton.usecase.core.leito;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.entity.core.leito.exception.LeitoNotFoundException;
import com.fiap.hackaton.entity.core.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.core.leito.model.Leito;
import com.fiap.hackaton.entity.core.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.core.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import com.fiap.hackaton.usecase.core.leito.dto.ILeitoUpdateData;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class UpdateLeitoUseCase {

    private final LeitoGateway leitoGateway;
    private final HospitalGateway hospitalGateway;
    private final PacienteGateway pacienteGateway;

    public UpdateLeitoUseCase(LeitoGateway leitoGateway, HospitalGateway hospitalGateway, PacienteGateway pacienteGateway) {
        this.leitoGateway = leitoGateway;
        this.hospitalGateway = hospitalGateway;
        this.pacienteGateway = pacienteGateway;
    }

    @Transactional
    public Leito execute(UUID id, ILeitoUpdateData dados) throws LeitoNotFoundException, PacienteNotFoundException, HospitalNotFoundException {
        Leito leito = this.leitoGateway.findById(id)
                .orElseThrow(() -> new LeitoNotFoundException("Leito not found: " + id));

        Hospital hospital = this.hospitalGateway.findById(dados.hospitalId()).orElseThrow(() -> new HospitalNotFoundException("Hospital not found: " + dados.hospitalId()));

        Paciente paciente = this.pacienteGateway.findById(dados.pacienteId())
                .orElseThrow(() -> new PacienteNotFoundException("Paciente not found: " + dados.pacienteId()));

        if (dados.identificacao() != null && !dados.identificacao().isBlank())
            leito.setIdentificacao(dados.identificacao());

        if (dados.pavilhao() != null && !dados.pavilhao().isBlank())
            leito.setPavilhao(dados.pavilhao());

        if (dados.quarto() != null && !dados.quarto().isBlank())
            leito.setQuarto(dados.quarto());

        if (hospital != null)
            leito.setHospitalId(dados.hospitalId());

        if (paciente != null)
            leito.setPacienteId(dados.pacienteId());

        return this.leitoGateway.update(leito);
    }

}
