package com.fiap.hackaton.usecase.leito;

import com.fiap.hackaton.entity.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.usecase.leito.dto.ILeitoRegistrationData;

public class CreateLeitoUseCase {

    private final LeitoGateway leitoGateway;

    public CreateLeitoUseCase(LeitoGateway leitoGateway) {
        this.leitoGateway = leitoGateway;
    }

    public Leito execute(ILeitoRegistrationData dados) {

        Leito leito = new Leito(dados.identificacao(), dados.pavilhao(), dados.quarto(),
                dados.hospital(), dados.paciente());

        return this.leitoGateway.save(leito);
    }

}