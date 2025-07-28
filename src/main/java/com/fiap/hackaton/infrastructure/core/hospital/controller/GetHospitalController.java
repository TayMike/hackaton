package com.fiap.hackaton.infrastructure.core.hospital.controller;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.core.hospital.dto.HospitalPublicData;
import com.fiap.hackaton.usecase.core.hospital.GetHospitalUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetHospitalController {

    private final GetHospitalUseCase getHospitalUseCase;

    public GetHospitalController(GetHospitalUseCase getHospitalUseCase) {
        this.getHospitalUseCase = getHospitalUseCase;
    }

    @GetMapping("/hospitais/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HospitalPublicData getHospital(@PathVariable UUID id) throws HospitalNotFoundException {
        Hospital hospital = getHospitalUseCase.execute(id);
        return new HospitalPublicData(hospital);
    }

}
