package com.fiap.hackaton.usecase.core.hospital;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class GetHospitalUseCase {

    private final HospitalGateway hospitalGateway;

    public GetHospitalUseCase(HospitalGateway hospitalGateway) {
        this.hospitalGateway = hospitalGateway;
    }

    @Transactional(readOnly = true)
    public Hospital execute(UUID id) throws HospitalNotFoundException {
        return hospitalGateway.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found: " + id));
    }

}
