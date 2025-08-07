package com.fiap.hackaton.usecase.core.hospital;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.usecase.core.hospital.dto.IHospitalUpdateData;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class UpdateHospitalUseCase {

    private final HospitalGateway hospitalGateway;

    public UpdateHospitalUseCase(HospitalGateway hospitalGateway) {
        this.hospitalGateway = hospitalGateway;
    }

    @Transactional
    public Hospital execute(UUID id, IHospitalUpdateData dados) throws HospitalNotFoundException {
        Hospital hospital = this.hospitalGateway.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found: " + id));

        if (!dados.colaboradoresIds().isEmpty())
            hospital.setColaboradoresIds(dados.colaboradoresIds());

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
