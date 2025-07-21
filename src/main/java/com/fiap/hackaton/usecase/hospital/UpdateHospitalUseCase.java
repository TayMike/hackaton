package com.fiap.hackaton.usecase.hospital;

import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.usecase.hospital.dto.IHospitalUpdateData;

import java.util.List;
import java.util.UUID;

public class UpdateHospitalUseCase {

    private final HospitalGateway hospitalGateway;
    private final ColaboradorGateway colaboradorGateway;

    public UpdateHospitalUseCase(HospitalGateway hospitalGateway, ColaboradorGateway colaboradorGateway) {
        this.hospitalGateway = hospitalGateway;
        this.colaboradorGateway = colaboradorGateway;
    }

    public Hospital execute(UUID id, IHospitalUpdateData dados) throws HospitalNotFoundException {
        Hospital hospital = this.hospitalGateway.findById(id)
                .orElseThrow(HospitalNotFoundException::new).toHospital();

        List<ColaboradorSchema> colaboradorSchema = this.colaboradorGateway.findAll()
                .stream()
                .filter(colaborador -> dados.colaboradores().contains(colaborador.getId()))
                .toList();
        List<Colaborador> colaboradores = colaboradorSchema.stream()
                .map(ColaboradorSchema::toColaborador)
                .toList();

        if (!colaboradorSchema.isEmpty())
            hospital.setColaboradores(colaboradores);

        if (dados.cep() != null && !dados.cep().isBlank())
            hospital.setCep(dados.cep());

        if (dados.numero() != null)
            hospital.setNumero(dados.numero());

        if (dados.quantidadeLeitoAtual() != null)
            hospital.setQuantidadeLeitoAtual(dados.quantidadeLeitoAtual());

        if (dados.quantidadeLeitoMaximo() != null)
            hospital.setQuantidadeLeitoMaximo(dados.quantidadeLeitoMaximo());

        return this.hospitalGateway.update(hospital, colaboradorSchema).toHospital();
    }

}
