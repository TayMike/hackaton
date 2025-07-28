package com.fiap.hackaton.infrastructure.insumoDominio.coletaInsumo.controller;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.exception.EstoqueInsumoNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.exception.InsufficientStockException;
import com.fiap.hackaton.infrastructure.insumoDominio.coletaInsumo.dto.ColetaInsumoPublicData;
import com.fiap.hackaton.infrastructure.insumoDominio.coletaInsumo.dto.ColetaInsumoRegistrationData;
import com.fiap.hackaton.usecase.insumoDominio.coletaInsumo.CreateColetaInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateColetaInsumoController {

    private final CreateColetaInsumoUseCase createColetaInsumoUseCase;

    public CreateColetaInsumoController(CreateColetaInsumoUseCase createColetaInsumoUseCase) {
        this.createColetaInsumoUseCase = createColetaInsumoUseCase;
    }

    @PostMapping("/coleta-insumos")
    @ResponseStatus(HttpStatus.CREATED)
    public ColetaInsumoPublicData createColetaInsumo(@RequestBody ColetaInsumoRegistrationData dados) throws HospitalNotFoundException, PacienteNotFoundException, ColaboradorNotFoundException, InsufficientStockException, EstoqueInsumoNotFoundException {
        return new ColetaInsumoPublicData(createColetaInsumoUseCase.execute(dados));
    }

}
