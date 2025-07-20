package com.fiap.hackaton.infrastructure.paciente.controller;

import com.fiap.hackaton.entity.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.infrastructure.paciente.dto.PacientePublicData;
import com.fiap.hackaton.infrastructure.paciente.dto.PacienteUpdateData;
import com.fiap.hackaton.usecase.paciente.UpdatePacienteUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UpdatePacienteController {

    private final UpdatePacienteUseCase updatePacienteUseCase;

    public UpdatePacienteController(UpdatePacienteUseCase updatePacienteUseCase) {
        this.updatePacienteUseCase = updatePacienteUseCase;
    }

    @PutMapping("/admins/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PacientePublicData updatePaciente(@PathVariable UUID id, @Valid @RequestBody PacienteUpdateData dados) throws PacienteNotFoundException {
        return new PacientePublicData(updatePacienteUseCase.execute(id, dados));
    }

}
