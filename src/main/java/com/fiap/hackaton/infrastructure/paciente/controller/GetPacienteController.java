package com.fiap.hackaton.infrastructure.paciente.controller;

import com.fiap.hackaton.entity.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.infrastructure.paciente.dto.PacientePublicData;
import com.fiap.hackaton.usecase.paciente.GetPacienteUseCase;
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
    @ResponseStatus(HttpStatus.CREATED)
    public PacientePublicData getPaciente(@PathVariable UUID id) throws PacienteNotFoundException {
        Paciente paciente = getPacienteUseCase.execute(id);
        return new PacientePublicData(paciente);
    }

}
