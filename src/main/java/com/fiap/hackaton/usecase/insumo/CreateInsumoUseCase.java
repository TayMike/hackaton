package com.fiap.hackaton.usecase.insumo;

import com.fiap.hackaton.entity.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.usecase.insumo.dto.IInsumoRegistrationData;

public class CreateInsumoUseCase {

    private final InsumoGateway insumoGateway;

    public CreateInsumoUseCase(InsumoGateway insumoGateway) {
        this.insumoGateway = insumoGateway;
    }

    public Insumo execute(IInsumoRegistrationData dados) {

        Insumo insumo = new Insumo(dados.nome(), dados.custo(), dados.quantidade(),
                dados.peso(), dados.validade(), dados.marca(), dados.unidadeMedida());

        return this.insumoGateway.save(insumo);
    }

}
