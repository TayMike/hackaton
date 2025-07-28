package com.fiap.hackaton.infrastructure.auxiliar.configuration.web;

import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.core.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.gateway.EntregaEquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.gateway.ColetaInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.gateway.EntregaInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.*;
import com.fiap.hackaton.infrastructure.core.colaborador.gateway.ColaboradorDatabaseGateway;
import com.fiap.hackaton.infrastructure.core.hospital.gateway.HospitalDatabaseGateway;
import com.fiap.hackaton.infrastructure.core.leito.gateway.LeitoDatabaseGateway;
import com.fiap.hackaton.infrastructure.core.paciente.gateway.PacienteDatabaseGateway;
import com.fiap.hackaton.infrastructure.equipamentoDominio.entregaEquipamento.gateway.EntregaEquipamentoDatabaseGateway;
import com.fiap.hackaton.infrastructure.equipamentoDominio.equipamento.gateway.EquipamentoDatabaseGateway;
import com.fiap.hackaton.infrastructure.equipamentoDominio.estoqueEquipamento.gateway.EstoqueEquipamentoDatabaseGateway;
import com.fiap.hackaton.infrastructure.insumoDominio.coletaInsumo.gateway.ColetaInsumoDatabaseGateway;
import com.fiap.hackaton.infrastructure.insumoDominio.entregaInsumo.gateway.EntregaInsumoDatabaseGateway;
import com.fiap.hackaton.infrastructure.insumoDominio.estoqueInsumo.gateway.EstoqueInsumoDatabaseGateway;
import com.fiap.hackaton.infrastructure.insumoDominio.insumo.gateway.InsumoDatabaseGateway;
import com.fiap.hackaton.usecase.core.colaborador.CreateColaboradorUseCase;
import com.fiap.hackaton.usecase.core.colaborador.GetColaboradorUseCase;
import com.fiap.hackaton.usecase.core.colaborador.SearchColaboradorUseCase;
import com.fiap.hackaton.usecase.core.colaborador.UpdateColaboradorUseCase;
import com.fiap.hackaton.usecase.core.hospital.*;
import com.fiap.hackaton.usecase.core.leito.*;
import com.fiap.hackaton.usecase.core.paciente.CreatePacienteUseCase;
import com.fiap.hackaton.usecase.core.paciente.GetPacienteUseCase;
import com.fiap.hackaton.usecase.core.paciente.SearchPacienteUseCase;
import com.fiap.hackaton.usecase.core.paciente.UpdatePacienteUseCase;
import com.fiap.hackaton.usecase.equipamentoDominio.entregaEquipamento.CreateEntregaEquipamentoUseCase;
import com.fiap.hackaton.usecase.equipamentoDominio.entregaEquipamento.GetEntregaEquipamentoUseCase;
import com.fiap.hackaton.usecase.equipamentoDominio.entregaEquipamento.SearchEntregaEquipamentoUseCase;
import com.fiap.hackaton.usecase.equipamentoDominio.equipamento.*;
import com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento.*;
import com.fiap.hackaton.usecase.insumoDominio.coletaInsumo.CreateColetaInsumoUseCase;
import com.fiap.hackaton.usecase.insumoDominio.coletaInsumo.GetColetaInsumoUseCase;
import com.fiap.hackaton.usecase.insumoDominio.coletaInsumo.SearchColetaInsumoUseCase;
import com.fiap.hackaton.usecase.insumoDominio.entregaInsumo.CreateEntregaInsumoUseCase;
import com.fiap.hackaton.usecase.insumoDominio.entregaInsumo.GetEntregaInsumoUseCase;
import com.fiap.hackaton.usecase.insumoDominio.entregaInsumo.SearchEntregaInsumoUseCase;
import com.fiap.hackaton.usecase.insumoDominio.estoqueInsumo.GetEstoqueInsumoUseCase;
import com.fiap.hackaton.usecase.insumoDominio.estoqueInsumo.SearchEstoqueInsumoUseCase;
import com.fiap.hackaton.usecase.insumoDominio.insumo.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

import java.util.ResourceBundle;

@Configuration
public class MvcConfig {

    @Bean
    public ResourceBundle messageBundle() {
        return ResourceBundle.getBundle("ValidationMessages");
    }

    @Bean
    public LocaleResolver sessionLocaleResolver() {
        return new AcceptHeaderResolver();
    }

    // --- Colaborador ---
    @Bean
    public CreateColaboradorUseCase createColaboradorUseCase(ColaboradorRepository colaboradorRepository,
                                                             HospitalRepository hospitalRepository) {
        ColaboradorGateway colaboradorGateway = new ColaboradorDatabaseGateway(colaboradorRepository);
        HospitalGateway hospitalGateway = new HospitalDatabaseGateway(hospitalRepository);
        return new CreateColaboradorUseCase(colaboradorGateway, hospitalGateway);
    }

    @Bean
    public GetColaboradorUseCase getColaboradorUseCase(ColaboradorRepository colaboradorRepository) {
        ColaboradorGateway colaboradorGateway = new ColaboradorDatabaseGateway(colaboradorRepository);
        return new GetColaboradorUseCase(colaboradorGateway);
    }

    @Bean
    public SearchColaboradorUseCase searchColaboradorUseCase(ColaboradorRepository colaboradorRepository) {
        ColaboradorGateway colaboradorGateway = new ColaboradorDatabaseGateway(colaboradorRepository);
        return new SearchColaboradorUseCase(colaboradorGateway);
    }

    @Bean
    public UpdateColaboradorUseCase updateColaboradorUseCase(ColaboradorRepository colaboradorRepository,
                                                             HospitalRepository hospitalRepository) {
        ColaboradorGateway colaboradorGateway = new ColaboradorDatabaseGateway(colaboradorRepository);
        HospitalGateway hospitalGateway = new HospitalDatabaseGateway(hospitalRepository);
        return new UpdateColaboradorUseCase(colaboradorGateway, hospitalGateway);
    }

    // --- ColetaInsumo ---
    @Bean
    public CreateColetaInsumoUseCase createColetaInsumoUseCase(ColetaInsumoRepository coletaInsumoRepository,
                                                               ColaboradorRepository colaboradorRepository,
                                                               PacienteRepository pacienteRepository,
                                                               HospitalRepository hospitalRepository,
                                                               EstoqueInsumoRepository estoqueInsumoRepository) {
        ColetaInsumoGateway coletaInsumoGateway = new ColetaInsumoDatabaseGateway(coletaInsumoRepository);
        ColaboradorGateway colaboradorGateway = new ColaboradorDatabaseGateway(colaboradorRepository);
        PacienteGateway pacienteGateway = new PacienteDatabaseGateway(pacienteRepository);
        HospitalGateway hospitalGateway = new HospitalDatabaseGateway(hospitalRepository);
        EstoqueInsumoGateway estoqueInsumoDatabaseGateway = new EstoqueInsumoDatabaseGateway(estoqueInsumoRepository);
        return new CreateColetaInsumoUseCase(coletaInsumoGateway, colaboradorGateway, pacienteGateway,
                hospitalGateway, estoqueInsumoDatabaseGateway);
    }

    @Bean
    public GetColetaInsumoUseCase getColetaInsumoUseCase(ColetaInsumoRepository coletaInsumoRepository) {
        ColetaInsumoGateway coletaInsumoGateway = new ColetaInsumoDatabaseGateway(coletaInsumoRepository);
        return new GetColetaInsumoUseCase(coletaInsumoGateway);
    }

    @Bean
    public SearchColetaInsumoUseCase searchColetaInsumoUseCase(ColetaInsumoRepository coletaInsumoRepository) {
        ColetaInsumoGateway coletaInsumoGateway = new ColetaInsumoDatabaseGateway(coletaInsumoRepository);
        return new SearchColetaInsumoUseCase(coletaInsumoGateway);
    }

    // --- EntregaEquipamento ---
    @Bean
    public CreateEntregaEquipamentoUseCase createEntregaEquipamentoUseCase(EntregaEquipamentoRepository entregaEquipamentoRepository,
                                                                           EquipamentoRepository equipamentoRepository,
                                                                           ColaboradorRepository colaboradorRepository,
                                                                           HospitalRepository hospitalRepository) {
        EntregaEquipamentoGateway entregaEquipamentoGateway = new EntregaEquipamentoDatabaseGateway(entregaEquipamentoRepository);
        EquipamentoGateway equipamentoGateway = new EquipamentoDatabaseGateway(equipamentoRepository);
        ColaboradorGateway colaboradorGateway = new ColaboradorDatabaseGateway(colaboradorRepository);
        HospitalGateway hospitalGateway = new HospitalDatabaseGateway(hospitalRepository);
        return new CreateEntregaEquipamentoUseCase(entregaEquipamentoGateway, equipamentoGateway, colaboradorGateway, hospitalGateway);
    }

    @Bean
    public GetEntregaEquipamentoUseCase getEntregaEquipamentoUseCase(EntregaEquipamentoRepository entregaEquipamentoRepository) {
        EntregaEquipamentoGateway entregaEquipamentoGateway = new EntregaEquipamentoDatabaseGateway(entregaEquipamentoRepository);
        return new GetEntregaEquipamentoUseCase(entregaEquipamentoGateway);
    }

    @Bean
    public SearchEntregaEquipamentoUseCase searchEntregaEquipamentoUseCase(EntregaEquipamentoRepository entregaEquipamentoRepository) {
        EntregaEquipamentoGateway entregaEquipamentoGateway = new EntregaEquipamentoDatabaseGateway(entregaEquipamentoRepository);
        return new SearchEntregaEquipamentoUseCase(entregaEquipamentoGateway);
    }

    // --- EntregaInsumo ---
    @Bean
    public CreateEntregaInsumoUseCase createEntregaInsumoUseCase(EntregaInsumoRepository entregaInsumoRepository,
                                                                 ColaboradorRepository colaboradorRepository,
                                                                 HospitalRepository hospitalRepository,
                                                                 EstoqueInsumoRepository estoqueInsumoRepository) {
        EntregaInsumoGateway entregaInsumoGateway = new EntregaInsumoDatabaseGateway(entregaInsumoRepository);
        ColaboradorGateway colaboradorGateway = new ColaboradorDatabaseGateway(colaboradorRepository);
        HospitalGateway hospitalGateway = new HospitalDatabaseGateway(hospitalRepository);
        EstoqueInsumoGateway estoqueInsumoGateway = new EstoqueInsumoDatabaseGateway(estoqueInsumoRepository);
        return new CreateEntregaInsumoUseCase(entregaInsumoGateway, colaboradorGateway, hospitalGateway, estoqueInsumoGateway);
    }

    @Bean
    public GetEntregaInsumoUseCase getEntregaInsumoUseCase(EntregaInsumoRepository entregaInsumoRepository) {
        EntregaInsumoGateway entregaInsumoGateway = new EntregaInsumoDatabaseGateway(entregaInsumoRepository);
        return new GetEntregaInsumoUseCase(entregaInsumoGateway);
    }

    @Bean
    public SearchEntregaInsumoUseCase searchEntregaInsumoUseCase(EntregaInsumoRepository entregaInsumoRepository) {
        EntregaInsumoGateway entregaInsumoGateway = new EntregaInsumoDatabaseGateway(entregaInsumoRepository);
        return new SearchEntregaInsumoUseCase(entregaInsumoGateway);
    }

    // --- Equipamento ---
    @Bean
    public CreateEquipamentoUseCase createEquipamentoUseCase(EquipamentoRepository equipamentoRepository) {
        EquipamentoGateway equipamentoGateway = new EquipamentoDatabaseGateway(equipamentoRepository);
        return new CreateEquipamentoUseCase(equipamentoGateway);
    }

    @Bean
    public DeleteEquipamentoUseCase deleteEquipamentoUseCase(EquipamentoRepository equipamentoRepository) {
        EquipamentoGateway equipamentoGateway = new EquipamentoDatabaseGateway(equipamentoRepository);
        return new DeleteEquipamentoUseCase(equipamentoGateway);
    }

    @Bean
    public GetEquipamentoUseCase getEquipamentoUseCase(EquipamentoRepository equipamentoRepository) {
        EquipamentoGateway equipamentoGateway = new EquipamentoDatabaseGateway(equipamentoRepository);
        return new GetEquipamentoUseCase(equipamentoGateway);
    }

    @Bean
    public SearchEquipamentoUseCase searchEquipamentoUseCase(EquipamentoRepository equipamentoRepository) {
        EquipamentoGateway equipamentoGateway = new EquipamentoDatabaseGateway(equipamentoRepository);
        return new SearchEquipamentoUseCase(equipamentoGateway);
    }

    @Bean
    public UpdateEquipamentoUseCase updateEquipamentoUseCase(EquipamentoRepository equipamentoRepository) {
        EquipamentoGateway equipamentoGateway = new EquipamentoDatabaseGateway(equipamentoRepository);
        return new UpdateEquipamentoUseCase(equipamentoGateway);
    }

    // --- EstoqueEquipamento ---
    @Bean
    public CreateEstoqueEquipamentoUseCase createEstoqueEquipamentoUseCase(EstoqueEquipamentoRepository estoqueEquipamentoRepository,
                                                                           EquipamentoRepository equipamentoRepository,
                                                                           ColaboradorRepository colaboradorRepository,
                                                                           HospitalRepository hospitalRepository) {
        EstoqueEquipamentoGateway estoqueEquipamentoGateway = new EstoqueEquipamentoDatabaseGateway(estoqueEquipamentoRepository);
        EquipamentoGateway equipamentoGateway = new EquipamentoDatabaseGateway(equipamentoRepository);
        ColaboradorGateway colaboradorGateway = new ColaboradorDatabaseGateway(colaboradorRepository);
        HospitalGateway hospitalGateway = new HospitalDatabaseGateway(hospitalRepository);
        return new CreateEstoqueEquipamentoUseCase(estoqueEquipamentoGateway, equipamentoGateway, colaboradorGateway, hospitalGateway);
    }

    @Bean
    public DeleteEstoqueEquipamentoUseCase deleteEstoqueEquipamentoUseCase(EstoqueEquipamentoRepository estoqueEquipamentoRepository) {
        EstoqueEquipamentoGateway estoqueEquipamentoGateway = new EstoqueEquipamentoDatabaseGateway(estoqueEquipamentoRepository);
        return new DeleteEstoqueEquipamentoUseCase(estoqueEquipamentoGateway);
    }

    @Bean
    public GetEstoqueEquipamentoUseCase getEstoqueEquipamentoUseCase(EstoqueEquipamentoRepository estoqueEquipamentoRepository) {
        EstoqueEquipamentoGateway estoqueEquipamentoGateway = new EstoqueEquipamentoDatabaseGateway(estoqueEquipamentoRepository);
        return new GetEstoqueEquipamentoUseCase(estoqueEquipamentoGateway);
    }

    @Bean
    public SearchEstoqueEquipamentoUseCase searchEstoqueEquipamentoUseCase(EstoqueEquipamentoRepository estoqueEquipamentoRepository) {
        EstoqueEquipamentoGateway estoqueEquipamentoGateway = new EstoqueEquipamentoDatabaseGateway(estoqueEquipamentoRepository);
        return new SearchEstoqueEquipamentoUseCase(estoqueEquipamentoGateway);
    }

    @Bean
    public UpdateEstoqueEquipamentoUseCase updateEstoqueEquipamentoUseCase(EstoqueEquipamentoRepository estoqueEquipamentoRepository,
                                                                           ColaboradorRepository colaboradorRepository) {
        EstoqueEquipamentoGateway estoqueEquipamentoGateway = new EstoqueEquipamentoDatabaseGateway(estoqueEquipamentoRepository);
        ColaboradorGateway colaboradorGateway = new ColaboradorDatabaseGateway(colaboradorRepository);
        return new UpdateEstoqueEquipamentoUseCase(estoqueEquipamentoGateway, colaboradorGateway);
    }

    // --- EstoqueInsumo ---
    @Bean
    public GetEstoqueInsumoUseCase getEstoqueInsumoUseCase(EstoqueInsumoRepository estoqueInsumoRepository) {
        EstoqueInsumoGateway estoqueInsumoGateway = new EstoqueInsumoDatabaseGateway(estoqueInsumoRepository);
        return new GetEstoqueInsumoUseCase(estoqueInsumoGateway);
    }

    @Bean
    public SearchEstoqueInsumoUseCase searchEstoqueInsumoUseCase(EstoqueInsumoRepository estoqueInsumoRepository) {
        EstoqueInsumoGateway estoqueInsumoGateway = new EstoqueInsumoDatabaseGateway(estoqueInsumoRepository);
        return new SearchEstoqueInsumoUseCase(estoqueInsumoGateway);
    }

    // --- Hospital ---
    @Bean
    public CreateHospitalUseCase createHospitalUseCase(HospitalRepository hospitalRepository) {
        HospitalGateway hospitalGateway = new HospitalDatabaseGateway(hospitalRepository);
        return new CreateHospitalUseCase(hospitalGateway);
    }

    @Bean
    public DeleteHospitalUseCase deleteHospitalUseCase(HospitalRepository hospitalRepository) {
        HospitalGateway hospitalGateway = new HospitalDatabaseGateway(hospitalRepository);
        return new DeleteHospitalUseCase(hospitalGateway);
    }

    @Bean
    public GetHospitalUseCase getHospitalUseCase(HospitalRepository hospitalRepository) {
        HospitalGateway hospitalGateway = new HospitalDatabaseGateway(hospitalRepository);
        return new GetHospitalUseCase(hospitalGateway);
    }

    @Bean
    public SearchHospitalUseCase searchHospitalUseCase(HospitalRepository hospitalRepository) {
        HospitalGateway hospitalGateway = new HospitalDatabaseGateway(hospitalRepository);
        return new SearchHospitalUseCase(hospitalGateway);
    }

    @Bean
    public UpdateHospitalUseCase updateHospitalUseCase(HospitalRepository hospitalRepository) {
        HospitalGateway hospitalGateway = new HospitalDatabaseGateway(hospitalRepository);
        return new UpdateHospitalUseCase(hospitalGateway);
    }

    // --- Insumo ---
    @Bean
    public CreateInsumoUseCase createInsumoUseCase(InsumoRepository insumoRepository) {
        InsumoGateway insumoGateway = new InsumoDatabaseGateway(insumoRepository);
        return new CreateInsumoUseCase(insumoGateway);
    }

    @Bean
    public DeleteInsumoUseCase deleteInsumoUseCase(InsumoRepository insumoRepository) {
        InsumoGateway insumoGateway = new InsumoDatabaseGateway(insumoRepository);
        return new DeleteInsumoUseCase(insumoGateway);
    }

    @Bean
    public GetInsumoUseCase getInsumoUseCase(InsumoRepository insumoRepository) {
        InsumoGateway insumoGateway = new InsumoDatabaseGateway(insumoRepository);
        return new GetInsumoUseCase(insumoGateway);
    }

    @Bean
    public SearchInsumoUseCase searchInsumoUseCase(InsumoRepository insumoRepository) {
        InsumoGateway insumoGateway = new InsumoDatabaseGateway(insumoRepository);
        return new SearchInsumoUseCase(insumoGateway);
    }

    @Bean
    public UpdateInsumoUseCase updateInsumoUseCase(InsumoRepository insumoRepository) {
        InsumoGateway insumoGateway = new InsumoDatabaseGateway(insumoRepository);
        return new UpdateInsumoUseCase(insumoGateway);
    }

    // --- Leito ---
    @Bean
    public CreateLeitoUseCase createLeitoUseCase(LeitoRepository leitoRepository,
                                                 HospitalRepository hospitalRepository,
                                                 PacienteRepository pacienteRepository) {
        LeitoGateway leitoGateway = new LeitoDatabaseGateway(leitoRepository);
        HospitalGateway hospitalGateway = new HospitalDatabaseGateway(hospitalRepository);
        PacienteGateway pacienteGateway = new PacienteDatabaseGateway(pacienteRepository);
        return new CreateLeitoUseCase(leitoGateway, hospitalGateway, pacienteGateway);
    }

    @Bean
    public DeleteLeitoUseCase deleteLeitoUseCase(LeitoRepository leitoRepository) {
        LeitoGateway leitoGateway = new LeitoDatabaseGateway(leitoRepository);
        return new DeleteLeitoUseCase(leitoGateway);
    }

    @Bean
    public GetLeitoUseCase getLeitoUseCase(LeitoRepository leitoRepository) {
        LeitoGateway leitoGateway = new LeitoDatabaseGateway(leitoRepository);
        return new GetLeitoUseCase(leitoGateway);
    }

    @Bean
    public SearchLeitoUseCase searchLeitoUseCase(LeitoRepository leitoRepository) {
        LeitoGateway leitoGateway = new LeitoDatabaseGateway(leitoRepository);
        return new SearchLeitoUseCase(leitoGateway);
    }

    @Bean
    public UpdateLeitoUseCase updateLeitoUseCase(LeitoRepository leitoRepository,
                                                 HospitalRepository hospitalRepository,
                                                 PacienteRepository pacienteRepository) {
        LeitoGateway leitoGateway = new LeitoDatabaseGateway(leitoRepository);
        HospitalGateway hospitalGateway = new HospitalDatabaseGateway(hospitalRepository);
        PacienteGateway pacienteGateway = new PacienteDatabaseGateway(pacienteRepository);
        return new UpdateLeitoUseCase(leitoGateway, hospitalGateway, pacienteGateway);
    }

    // --- Paciente ---
    @Bean
    public CreatePacienteUseCase createPacienteUseCase(PacienteRepository pacienteRepository) {
        PacienteGateway pacienteGateway = new PacienteDatabaseGateway(pacienteRepository);
        return new CreatePacienteUseCase(pacienteGateway);
    }

    @Bean
    public GetPacienteUseCase getPacienteUseCase(PacienteRepository pacienteRepository) {
        PacienteGateway pacienteGateway = new PacienteDatabaseGateway(pacienteRepository);
        return new GetPacienteUseCase(pacienteGateway);
    }

    @Bean
    public SearchPacienteUseCase searchPacienteUseCase(PacienteRepository pacienteRepository) {
        PacienteGateway pacienteGateway = new PacienteDatabaseGateway(pacienteRepository);
        return new SearchPacienteUseCase(pacienteGateway);
    }

    @Bean
    public UpdatePacienteUseCase updatePacienteUseCase(PacienteRepository pacienteRepository) {
        PacienteGateway pacienteGateway = new PacienteDatabaseGateway(pacienteRepository);
        return new UpdatePacienteUseCase(pacienteGateway);
    }

}