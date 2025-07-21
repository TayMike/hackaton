package com.fiap.hackaton.usecase.leito;

import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.leito.exception.LeitoNotFoundException;
import com.fiap.hackaton.entity.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.entity.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.PacienteSchema;
import com.fiap.hackaton.usecase.leito.dto.ILeitoUpdateData;

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

    public Leito execute(UUID id, ILeitoUpdateData dados) throws LeitoNotFoundException, PacienteNotFoundException, HospitalNotFoundException {
        Leito leito = this.leitoGateway.findById(id)
                .orElseThrow(LeitoNotFoundException::new).toLeito();

        HospitalSchema hospitalSchema = this.hospitalGateway.findById(dados.hospital().getId()).orElseThrow(HospitalNotFoundException::new);

        PacienteSchema pacienteSchema = this.pacienteGateway.findById(dados.paciente().getId())
                .orElseThrow(PacienteNotFoundException::new);

        if (dados.identificacao() != null && !dados.identificacao().isBlank())
            leito.setIdentificacao(dados.identificacao());

        if (dados.pavilhao() != null && !dados.pavilhao().isBlank())
            leito.setPavilhao(dados.pavilhao());

        if (dados.quarto() != null && !dados.quarto().isBlank())
            leito.setQuarto(dados.quarto());

        if (hospitalSchema != null)
            leito.setHospital(hospitalSchema.toHospital());

        if (pacienteSchema != null)
            leito.setPaciente(pacienteSchema.toPaciente());

        return this.leitoGateway.update(leito, hospitalSchema, pacienteSchema).toLeito();
    }

}
