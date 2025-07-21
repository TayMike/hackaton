package com.fiap.hackaton.usecase.colaborador;

import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.colaborador.dto.IColaboradorRegistrationData;

public class CreateColaboradorUseCase {

    private final ColaboradorGateway colaboradorGateway;
    private final HospitalGateway hospitalGateway;

    public CreateColaboradorUseCase(ColaboradorGateway colaboradorGateway, HospitalGateway hospitalGateway) {
        this.colaboradorGateway = colaboradorGateway;
        this.hospitalGateway = hospitalGateway;
    }

    public Colaborador execute(IColaboradorRegistrationData dados) throws HospitalNotFoundException {

        HospitalSchema hospitalSchema = hospitalGateway.findById(dados.hospital())
                .orElseThrow(HospitalNotFoundException::new);

        Colaborador colaborador = new Colaborador(dados.cpf(), dados.nome(), dados.matricula(),
                dados.primeiroDiaCadastro(), dados.cep(), dados.numeroCasa(), hospitalSchema.toHospital(), dados.setor(), dados.ativo());

        return this.colaboradorGateway.save(colaborador, hospitalSchema).toColaborador();
    }

}