package com.fiap.hackaton.infrastructure.paciente.controller;

import com.fiap.hackaton.infrastructure.paciente.dto.PacientePublicData;
import com.fiap.hackaton.infrastructure.paciente.dto.PacienteRegistrationData;
import com.fiap.hackaton.usecase.paciente.CreatePacienteUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatePacienteController {

    private final CreatePacienteUseCase createPacienteUseCase;

    public CreatePacienteController(CreatePacienteUseCase createPacienteUseCase) {
        this.createPacienteUseCase = createPacienteUseCase;
    }

    @PostMapping("/pacientes")
    @ResponseStatus(HttpStatus.CREATED)
    public PacientePublicData createPaciente(@Valid @RequestBody PacienteRegistrationData dados) {
        return new PacientePublicData(createPacienteUseCase.execute(dados));
    }

}
