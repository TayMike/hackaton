package com.fiap.hackaton.usecase.core.hospital;

import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SearchHospitalUseCase {

    private final HospitalGateway hospitalGateway;

    public SearchHospitalUseCase(HospitalGateway hospitalGateway) {
        this.hospitalGateway = hospitalGateway;
    }

    @Transactional(readOnly = true)
    public List<Hospital> execute() {
        return this.hospitalGateway.findAll();
    }

}
