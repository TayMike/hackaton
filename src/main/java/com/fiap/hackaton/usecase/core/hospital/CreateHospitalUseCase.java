package com.fiap.hackaton.usecase.core.hospital;

import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.usecase.core.hospital.dto.IHospitalRegistrationData;
import org.springframework.transaction.annotation.Transactional;

public class CreateHospitalUseCase {

    private final HospitalGateway hospitalGateway;

    public CreateHospitalUseCase(HospitalGateway hospitalGateway) {
        this.hospitalGateway = hospitalGateway;
    }

    @Transactional
    public Hospital execute(IHospitalRegistrationData dados) {

        Hospital hospital = new Hospital(dados.nome(), dados.cnpj(), dados.colaboradoresIds(), dados.cep(), dados.numero(), dados.quantidadeLeitoAtual(), dados.quantidadeLeitoMaximo());

        return this.hospitalGateway.save(hospital);
    }

}
