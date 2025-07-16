package com.fiap.hackaton.usecase.hospital;

import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;

import java.util.List;

public class SearchHospitalUseCase {

    private final HospitalGateway hospitalGateway;

    public SearchHospitalUseCase(HospitalGateway hospitalGateway) {
        this.hospitalGateway = hospitalGateway;
    }

    public List<Hospital> execute() {
        return this.hospitalGateway.findAll();
    }

}
