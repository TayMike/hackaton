package com.fiap.hackaton.infrastructure.core.colaborador.controller;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.infrastructure.core.colaborador.dto.ColaboradorPublicData;
import com.fiap.hackaton.infrastructure.core.colaborador.dto.ColaboradorUpdateData;
import com.fiap.hackaton.usecase.core.colaborador.UpdateColaboradorUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UpdateColaboradorController {

    private final UpdateColaboradorUseCase updateColaboradorUseCase;

    public UpdateColaboradorController(UpdateColaboradorUseCase updateColaboradorUseCase) {
        this.updateColaboradorUseCase = updateColaboradorUseCase;
    }

    @PutMapping("/colaboradores/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ColaboradorPublicData updateColaborador(@PathVariable UUID id, @Valid @RequestBody ColaboradorUpdateData dados) throws ColaboradorNotFoundException, HospitalNotFoundException {
        return new ColaboradorPublicData(updateColaboradorUseCase.execute(id, dados));
    }

}
