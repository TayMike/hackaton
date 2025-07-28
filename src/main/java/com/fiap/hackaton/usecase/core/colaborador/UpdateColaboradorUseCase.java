package com.fiap.hackaton.usecase.core.colaborador;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.usecase.core.colaborador.dto.IColaboradorUpdateData;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class UpdateColaboradorUseCase {

    private final ColaboradorGateway colaboradorGateway;
    private final HospitalGateway hospitalGateway;

    public UpdateColaboradorUseCase(ColaboradorGateway colaboradorGateway, HospitalGateway hospitalGateway) {
        this.colaboradorGateway = colaboradorGateway;
        this.hospitalGateway = hospitalGateway;
    }

    @Transactional
    public Colaborador execute(UUID id, IColaboradorUpdateData dados) throws ColaboradorNotFoundException, HospitalNotFoundException {

        Hospital hospital = hospitalGateway.findById(dados.hospitalId())
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found: " + dados.hospitalId()));

        Colaborador colaborador = this.colaboradorGateway.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException("Colaborador not found: " + id));

        if (dados.nome() != null && !dados.nome().isBlank())
            colaborador.setNome(dados.nome());

        if (dados.cep() != null && !dados.cep().isBlank())
            colaborador.setCep(dados.cep());

        if (dados.numeroCasa() != null)
            colaborador.setNumeroCasa(dados.numeroCasa());

        if (dados.hospitalId() != null)
            colaborador.setHospitalId(hospital.getId());

        if (dados.setor() != null && !dados.setor().isBlank())
            colaborador.setSetor(dados.setor());

        if (dados.ultimoDiaCadastro() != null && dados.ultimoDiaCadastro().isBefore(colaborador.getPrimeiroDiaCadastro()))
            throw new IllegalArgumentException("Último dia não pode ser antes do primeiro dia");

        if (dados.ultimoDiaCadastro() != null)
            colaborador.setUltimoDiaCadastro(dados.ultimoDiaCadastro());

        return this.colaboradorGateway.update(colaborador);
    }

}