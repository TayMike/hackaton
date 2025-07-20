package com.fiap.hackaton.infrastructure.hospital.controller;

import com.fiap.hackaton.infrastructure.hospital.dto.HospitalPublicData;
import com.fiap.hackaton.infrastructure.hospital.dto.HospitalRegistrationData;
import com.fiap.hackaton.usecase.hospital.CreateHospitalUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateHospitalController {

    private final CreateHospitalUseCase createHospitalUseCase;

    public CreateHospitalController(CreateHospitalUseCase createHospitalUseCase) {
        this.createHospitalUseCase = createHospitalUseCase;
    }

    @PostMapping("/hospitais")
    @ResponseStatus(HttpStatus.CREATED)
    public HospitalPublicData createHospital(@RequestBody HospitalRegistrationData dados) {
        return new HospitalPublicData(createHospitalUseCase.execute(dados));
    }

}
