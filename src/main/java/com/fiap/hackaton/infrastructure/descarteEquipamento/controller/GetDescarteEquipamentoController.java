package com.fiap.hackaton.infrastructure.descarteEquipamento.controller;

import com.fiap.hackaton.entity.descarteEquipamento.exception.DescarteEquipamentoNotFoundException;
import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;
import com.fiap.hackaton.infrastructure.descarteEquipamento.dto.DescarteEquipamentoPublicData;
import com.fiap.hackaton.usecase.descarteEquipamento.GetDescarteEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetDescarteEquipamentoController {

    private final GetDescarteEquipamentoUseCase getDescarteEquipamentoUseCase;

    public GetDescarteEquipamentoController(GetDescarteEquipamentoUseCase getDescarteEquipamentoUseCase) {
        this.getDescarteEquipamentoUseCase = getDescarteEquipamentoUseCase;
    }

    @GetMapping("/descarteEquipamentos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DescarteEquipamentoPublicData getDescarteEquipamento(@PathVariable UUID id) throws DescarteEquipamentoNotFoundException {
        DescarteEquipamento descarteEquipamento = getDescarteEquipamentoUseCase.execute(id);
        return new DescarteEquipamentoPublicData(descarteEquipamento);
    }

}
