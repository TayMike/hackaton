package com.fiap.hackaton.usecase.hospital;

import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;

import java.util.UUID;

public class DeleteHospitalUseCase {

    private final HospitalGateway hospitalGateway;

    public DeleteHospitalUseCase(HospitalGateway hospitalGateway) {
        this.hospitalGateway = hospitalGateway;
    }

    public Hospital execute(UUID id) throws HospitalNotFoundException {
        Hospital hospital = hospitalGateway.findById(id)
                .orElseThrow(HospitalNotFoundException::new);

        hospitalGateway.deleteById(id);

        return hospital;
    }

}
