package com.fiap.hackaton.infrastructure.core.hospital.controller;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.infrastructure.core.hospital.dto.HospitalPublicData;
import com.fiap.hackaton.infrastructure.core.hospital.dto.HospitalUpdateData;
import com.fiap.hackaton.usecase.core.hospital.UpdateHospitalUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UpdateHospitalController {

    private final UpdateHospitalUseCase updateHospitalUseCase;

    public UpdateHospitalController(UpdateHospitalUseCase updateHospitalUseCase) {
        this.updateHospitalUseCase = updateHospitalUseCase;
    }

    @PutMapping("/hospitais/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HospitalPublicData updateHospital(@PathVariable UUID id, @RequestBody HospitalUpdateData dados) throws HospitalNotFoundException {
        return new HospitalPublicData(updateHospitalUseCase.execute(id, dados));
    }

}
