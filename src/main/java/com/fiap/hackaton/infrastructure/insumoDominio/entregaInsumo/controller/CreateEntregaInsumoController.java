package com.fiap.hackaton.infrastructure.insumoDominio.entregaInsumo.controller;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.infrastructure.insumoDominio.entregaInsumo.dto.EntregaInsumoPublicData;
import com.fiap.hackaton.infrastructure.insumoDominio.entregaInsumo.dto.EntregaInsumoRegistrationData;
import com.fiap.hackaton.usecase.insumoDominio.entregaInsumo.CreateEntregaInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateEntregaInsumoController {

    private final CreateEntregaInsumoUseCase createEntregaInsumoUseCase;

    public CreateEntregaInsumoController(CreateEntregaInsumoUseCase createEntregaInsumoUseCase) {
        this.createEntregaInsumoUseCase = createEntregaInsumoUseCase;
    }

    @PostMapping("/entrega-insumos")
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaInsumoPublicData createEntregaInsumo(@RequestBody EntregaInsumoRegistrationData dados) throws HospitalNotFoundException, ColaboradorNotFoundException {
        return new EntregaInsumoPublicData(createEntregaInsumoUseCase.execute(dados));
    }

}
