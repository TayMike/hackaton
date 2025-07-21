package com.fiap.hackaton.usecase.colaborador;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.colaborador.dto.IColaboradorUpdateData;

import java.util.UUID;

public class UpdateColaboradorUseCase {

    private final ColaboradorGateway colaboradorGateway;
    private final HospitalGateway hospitalGateway;

    public UpdateColaboradorUseCase(ColaboradorGateway colaboradorGateway, HospitalGateway hospitalGateway) {
        this.colaboradorGateway = colaboradorGateway;
        this.hospitalGateway = hospitalGateway;
    }

    public Colaborador execute(UUID id, IColaboradorUpdateData dados) throws ColaboradorNotFoundException, HospitalNotFoundException {

        HospitalSchema hospitalSchema = hospitalGateway.findById(dados.hospital())
                .orElseThrow(HospitalNotFoundException::new);

        Colaborador colaborador = this.colaboradorGateway.findById(id)
                .orElseThrow(ColaboradorNotFoundException::new).toColaborador();

        if (dados.nome() != null && !dados.nome().isBlank())
            colaborador.setNome(dados.nome());

        if (dados.cep() != null && !dados.cep().isBlank())
            colaborador.setCep(dados.cep());

        if (dados.numeroCasa() != null)
            colaborador.setNumeroCasa(dados.numeroCasa());

        if (dados.hospital() != null)
            colaborador.setHospital(hospitalSchema.toHospital());

        if (dados.setor() != null && !dados.setor().isBlank())
            colaborador.setSetor(dados.setor());

        if (dados.ativo() != null)
            colaborador.setAtivo(dados.ativo());

        return this.colaboradorGateway.update(colaborador, hospitalSchema);
    }

}