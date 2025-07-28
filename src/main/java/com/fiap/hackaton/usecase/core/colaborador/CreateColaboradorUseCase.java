package com.fiap.hackaton.usecase.core.colaborador;

import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.usecase.core.colaborador.dto.IColaboradorRegistrationData;
import org.springframework.transaction.annotation.Transactional;

public class CreateColaboradorUseCase {

    private final ColaboradorGateway colaboradorGateway;
    private final HospitalGateway hospitalGateway;

    public CreateColaboradorUseCase(ColaboradorGateway colaboradorGateway, HospitalGateway hospitalGateway) {
        this.colaboradorGateway = colaboradorGateway;
        this.hospitalGateway = hospitalGateway;
    }

    @Transactional
    public Colaborador execute(IColaboradorRegistrationData dados) throws HospitalNotFoundException {

        Hospital hospital = hospitalGateway.findById(dados.hospitalId())
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found: " + dados.hospitalId()));

        Colaborador colaborador = new Colaborador(dados.cpf(), dados.nome(), dados.matricula(),
                dados.cep(), dados.numeroCasa(), hospital.getId(), dados.setor(), dados.primeiroDiaCadastro(), dados.ultimoDiaCadastro());

        return this.colaboradorGateway.save(colaborador);
    }

}