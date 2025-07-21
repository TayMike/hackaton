package com.fiap.hackaton.usecase.hospital;

import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;

import java.util.UUID;

public class GetHospitalUseCase {

    private final HospitalGateway hospitalGateway;

    public GetHospitalUseCase(HospitalGateway hospitalGateway) {
        this.hospitalGateway = hospitalGateway;
    }

    public Hospital execute(UUID id) throws HospitalNotFoundException {
        return hospitalGateway.findById(id)
                .orElseThrow(HospitalNotFoundException::new)
                .toHospital();
    }

}
