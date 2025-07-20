package com.fiap.hackaton.usecase.descarteEquipamento;

import com.fiap.hackaton.entity.descarteEquipamento.exception.DescarteEquipamentoNotFoundException;
import com.fiap.hackaton.entity.descarteEquipamento.gateway.DescarteEquipamentoGateway;
import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;

import java.util.UUID;

public class GetDescarteEquipamentoUseCase {

    private final DescarteEquipamentoGateway descarteEquipamentoGateway;

    public GetDescarteEquipamentoUseCase(DescarteEquipamentoGateway descarteEquipamentoGateway) {
        this.descarteEquipamentoGateway = descarteEquipamentoGateway;
    }

    public DescarteEquipamento execute(UUID id) throws DescarteEquipamentoNotFoundException {
        return this.descarteEquipamentoGateway.findById(id).
                orElseThrow(DescarteEquipamentoNotFoundException::new);
    }

}
