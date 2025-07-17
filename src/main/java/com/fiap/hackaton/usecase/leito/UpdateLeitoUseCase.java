package com.fiap.hackaton.usecase.leito;

import com.fiap.hackaton.entity.leito.exception.LeitoNotFoundException;
import com.fiap.hackaton.entity.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.usecase.leito.dto.ILeitoUpdateData;

import java.util.UUID;

public class UpdateLeitoUseCase {

    private final LeitoGateway leitoGateway;

    public UpdateLeitoUseCase(LeitoGateway leitoGateway) {
        this.leitoGateway = leitoGateway;
    }

    public Leito execute(UUID id, ILeitoUpdateData dados) throws LeitoNotFoundException {
        Leito leito = this.leitoGateway.findById(id)
                .orElseThrow(LeitoNotFoundException::new);

        if (dados.identificacao() != null && !dados.identificacao().isBlank())
            leito.setIdentificacao(dados.identificacao());

        if (dados.pavilhao() != null && !dados.pavilhao().isBlank())
            leito.setPavilhao(dados.pavilhao());

        if (dados.quarto() != null && !dados.quarto().isBlank())
            leito.setQuarto(dados.quarto());

        if (dados.hospital() != null)
            leito.setHospital(dados.hospital());

        if (dados.paciente() != null)
            leito.setPaciente(dados.paciente());

        return this.leitoGateway.update(leito);
    }

}
