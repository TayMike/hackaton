package com.fiap.hackaton.usecase.coletaEquipamento;

import com.fiap.hackaton.entity.coletaEquipamento.exception.ColetaEquipamentoNotFoundException;
import com.fiap.hackaton.entity.coletaEquipamento.gateway.ColetaEquipamentoGateway;
import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;

import java.util.UUID;

public class GetColetaEquipamentoUseCase {

    private final ColetaEquipamentoGateway coletaEquipamentoGateway;

    public GetColetaEquipamentoUseCase(ColetaEquipamentoGateway coletaEquipamentoGateway) {
        this.coletaEquipamentoGateway = coletaEquipamentoGateway;
    }

    public ColetaEquipamento execute(UUID id) throws ColetaEquipamentoNotFoundException {
        return this.coletaEquipamentoGateway.findById(id).
                orElseThrow(ColetaEquipamentoNotFoundException::new).toColetaEquipamento();
    }

}
