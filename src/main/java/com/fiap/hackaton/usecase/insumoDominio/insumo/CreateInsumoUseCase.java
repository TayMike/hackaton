package com.fiap.hackaton.usecase.insumoDominio.insumo;

import com.fiap.hackaton.entity.insumoDominio.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo;
import com.fiap.hackaton.usecase.insumoDominio.insumo.dto.IInsumoRegistrationData;
import org.springframework.transaction.annotation.Transactional;

public class CreateInsumoUseCase {

    private final InsumoGateway insumoGateway;

    public CreateInsumoUseCase(InsumoGateway insumoGateway) {
        this.insumoGateway = insumoGateway;
    }

    @Transactional
    public Insumo execute(IInsumoRegistrationData dados) {

        Insumo insumo = new Insumo(dados.nome(), dados.custo(), dados.quantidade(),
                dados.peso(), dados.validade(), dados.marca(), dados.unidadeMedida());

        return this.insumoGateway.save(insumo);
    }

}
