package com.fiap.hackaton.usecase.insumoDominio.coletaInsumo;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.entity.core.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.core.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.gateway.ColetaInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.exception.EstoqueInsumoNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.exception.InsufficientStockException;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.usecase.insumoDominio.coletaInsumo.dto.IColetaInsumoRegistrationData;
import org.springframework.transaction.annotation.Transactional;

public class CreateColetaInsumoUseCase {

    private final ColetaInsumoGateway coletaInsumoGateway;
    private final ColaboradorGateway colaboradorGateway;
    private final PacienteGateway pacienteGateway;
    private final HospitalGateway hospitalGateway;
    private final EstoqueInsumoGateway estoqueInsumoGateway;

    public CreateColetaInsumoUseCase(ColetaInsumoGateway coletaInsumoGateway, ColaboradorGateway colaboradorGateway, PacienteGateway pacienteGateway, HospitalGateway hospitalGateway, EstoqueInsumoGateway estoqueInsumoGateway) {
        this.coletaInsumoGateway = coletaInsumoGateway;
        this.colaboradorGateway = colaboradorGateway;
        this.pacienteGateway = pacienteGateway;
        this.hospitalGateway = hospitalGateway;
        this.estoqueInsumoGateway = estoqueInsumoGateway;
    }

    @Transactional
    public ColetaInsumo execute(IColetaInsumoRegistrationData dados) throws ColaboradorNotFoundException, PacienteNotFoundException, HospitalNotFoundException, InsufficientStockException, EstoqueInsumoNotFoundException {

        Colaborador colaborador = colaboradorGateway.findById(dados.colaboradorEntregadorId())
                .orElseThrow(() -> new ColaboradorNotFoundException("Colaborador not found: " + dados.colaboradorEntregadorId()));

        Paciente paciente = pacienteGateway.findById(dados.pacienteRecebedorId())
                .orElseThrow(() -> new PacienteNotFoundException("Paciente not found: " + dados.pacienteRecebedorId()));

        Hospital hospital = hospitalGateway.findById(dados.hospitalId())
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found: " + dados.hospitalId()));

        ColetaInsumo coletaInsumo = new ColetaInsumo(dados.insumos(), dados.colaboradorEntregadorId(),
                dados.dataHoraColeta(), dados.pacienteRecebedorId(), dados.hospitalId());
        coletaInsumo = this.coletaInsumoGateway.save(coletaInsumo);

        EstoqueInsumo estoqueInsumo = estoqueInsumoGateway.findByHospitalId(dados.hospitalId())
                .orElseThrow(() -> new EstoqueInsumoNotFoundException("Estoque not found for hospital: " + dados.hospitalId()));
        estoqueInsumo.diminuirComColeta(coletaInsumo);
        this.estoqueInsumoGateway.save(estoqueInsumo);

        return coletaInsumo;
    }

}