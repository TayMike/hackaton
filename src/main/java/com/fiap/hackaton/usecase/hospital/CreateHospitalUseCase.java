package com.fiap.hackaton.usecase.hospital;

import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.usecase.hospital.dto.IHospitalRegistrationData;

import java.util.List;
import java.util.stream.Collectors;

public class CreateHospitalUseCase {

    private final HospitalGateway hospitalGateway;
    private final ColaboradorGateway colaboradorGateway;

    public CreateHospitalUseCase(HospitalGateway hospitalGateway, ColaboradorGateway colaboradorGateway) {
        this.hospitalGateway = hospitalGateway;
        this.colaboradorGateway = colaboradorGateway;
    }

    public Hospital execute(IHospitalRegistrationData dados) {

        List<ColaboradorSchema> colaboradoresSchema = this.colaboradorGateway.findAll()
                .stream()
                .filter(colaborador -> dados.colaboradores().contains(colaborador.getId()))
                .collect(Collectors.toList());
        List<Colaborador> colaboradores = colaboradoresSchema.stream()
                .map(ColaboradorSchema::toColaborador)
                .collect(Collectors.toList());

        Hospital hospital = new Hospital(colaboradores, dados.cep(), dados.numero(), dados.quantidadeLeitoAtual(), dados.quantidadeLeitoMaximo());

        return this.hospitalGateway.save(hospital, colaboradoresSchema).toHospital();
    }

}
