package com.fiap.hackaton.usecase.hospital;

import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.usecase.hospital.dto.IHospitalUpdateData;

import java.util.UUID;

public class UpdateHospitalUseCase {

    private final HospitalGateway hospitalGateway;

    public UpdateHospitalUseCase(HospitalGateway hospitalGateway) {
        this.hospitalGateway = hospitalGateway;
    }

    public Hospital execute(UUID id, IHospitalUpdateData dados) throws HospitalNotFoundException {
        Hospital hospital = this.hospitalGateway.findById(id)
                .orElseThrow(HospitalNotFoundException::new);

        if (dados.colaboradores() != null && !dados.colaboradores().isEmpty())
            hospital.setColaboradores(dados.colaboradores());

        if (dados.cep() != null && !dados.cep().isBlank())
            hospital.setCep(dados.cep());

        if (dados.numero() != null)
            hospital.setNumero(dados.numero());

        if (dados.quantidadeLeitoAtual() != null)
            hospital.setQuantidadeLeitoAtual(dados.quantidadeLeitoAtual());

        if (dados.quantidadeLeitoMaximo() != null)
            hospital.setQuantidadeLeitoMaximo(dados.quantidadeLeitoMaximo());

        return this.hospitalGateway.update(hospital);
    }

}
