package com.fiap.hackaton.infrastructure.core.leito.controller;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.infrastructure.core.leito.dto.LeitoPublicData;
import com.fiap.hackaton.infrastructure.core.leito.dto.LeitoRegistrationData;
import com.fiap.hackaton.usecase.core.leito.CreateLeitoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateLeitoController {

    private final CreateLeitoUseCase createLeitoUseCase;

    public CreateLeitoController(CreateLeitoUseCase createLeitoUseCase) {
        this.createLeitoUseCase = createLeitoUseCase;
    }

    @PostMapping("/leitos")
    @ResponseStatus(HttpStatus.CREATED)
    public LeitoPublicData createLeito(@RequestBody LeitoRegistrationData dados) throws HospitalNotFoundException, PacienteNotFoundException {
        return new LeitoPublicData(createLeitoUseCase.execute(dados));
    }

}
