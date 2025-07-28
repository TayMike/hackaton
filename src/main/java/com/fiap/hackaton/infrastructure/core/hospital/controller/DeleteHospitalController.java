package com.fiap.hackaton.infrastructure.core.hospital.controller;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.usecase.core.hospital.DeleteHospitalUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeleteHospitalController {

    private final DeleteHospitalUseCase deleteHospitalUserCase;

    public DeleteHospitalController(DeleteHospitalUseCase deleteHospitalUserCase) {
        this.deleteHospitalUserCase = deleteHospitalUserCase;
    }

    @DeleteMapping("/hospitais/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteHospital(@PathVariable UUID id) throws HospitalNotFoundException {
        deleteHospitalUserCase.execute(id);
    }

}
