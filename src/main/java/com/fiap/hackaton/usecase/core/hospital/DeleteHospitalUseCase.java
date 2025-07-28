package com.fiap.hackaton.usecase.core.hospital;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class DeleteHospitalUseCase {

    private final HospitalGateway hospitalGateway;

    public DeleteHospitalUseCase(HospitalGateway hospitalGateway) {
        this.hospitalGateway = hospitalGateway;
    }

    @Transactional
    public void execute(UUID id) throws HospitalNotFoundException {
        hospitalGateway.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found: " + id));

        hospitalGateway.deleteById(id);
    }

}
