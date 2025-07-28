package com.fiap.hackaton.infrastructure.core.paciente.controller;

import com.fiap.hackaton.entity.core.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import com.fiap.hackaton.infrastructure.core.paciente.dto.PacientePublicData;
import com.fiap.hackaton.usecase.core.paciente.GetPacienteUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetPacienteController {

    private final GetPacienteUseCase getPacienteUseCase;

    public GetPacienteController(GetPacienteUseCase getPacienteUseCase) {
        this.getPacienteUseCase = getPacienteUseCase;
    }

    @GetMapping("/pacientes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PacientePublicData getPaciente(@PathVariable UUID id) throws PacienteNotFoundException {
        Paciente paciente = getPacienteUseCase.execute(id);
        return new PacientePublicData(paciente);
    }

}
