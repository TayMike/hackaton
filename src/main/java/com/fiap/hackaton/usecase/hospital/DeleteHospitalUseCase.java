package com.fiap.hackaton.usecase.hospital;

import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;

import java.util.UUID;

public class DeleteHospitalUseCase {

    private final HospitalGateway hospitalGateway;

    public DeleteHospitalUseCase(HospitalGateway hospitalGateway) {
        this.hospitalGateway = hospitalGateway;
    }

    public void execute(UUID id) throws HospitalNotFoundException {
        hospitalGateway.findById(id)
                .orElseThrow(HospitalNotFoundException::new);

        hospitalGateway.deleteById(id);
    }

}
