package com.fiap.hackaton.usecase.hospital;

import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.usecase.hospital.dto.IHospitalRegistrationData;

public class CreateHospitalUseCase {

    private final HospitalGateway hospitalGateway;

    public CreateHospitalUseCase(HospitalGateway hospitalGateway) {
        this.hospitalGateway = hospitalGateway;
    }

    public Hospital execute(IHospitalRegistrationData dados) {

        Hospital hospital = new Hospital(dados.colaboradores(), dados.cep(), dados.numero());

        return this.hospitalGateway.save(hospital);
    }

}
