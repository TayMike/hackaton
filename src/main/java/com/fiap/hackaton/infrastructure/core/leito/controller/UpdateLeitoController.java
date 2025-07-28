package com.fiap.hackaton.infrastructure.core.leito.controller;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.leito.exception.LeitoNotFoundException;
import com.fiap.hackaton.entity.core.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.infrastructure.core.leito.dto.LeitoPublicData;
import com.fiap.hackaton.infrastructure.core.leito.dto.LeitoUpdateData;
import com.fiap.hackaton.usecase.core.leito.UpdateLeitoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UpdateLeitoController {

    private final UpdateLeitoUseCase updateLeitoUseCase;

    public UpdateLeitoController(UpdateLeitoUseCase updateLeitoUseCase) {
        this.updateLeitoUseCase = updateLeitoUseCase;
    }

    @PutMapping("/leitos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeitoPublicData updateLeito(@PathVariable UUID id, @RequestBody LeitoUpdateData dados) throws LeitoNotFoundException, HospitalNotFoundException, PacienteNotFoundException {
        return new LeitoPublicData(updateLeitoUseCase.execute(id, dados));
    }

}
