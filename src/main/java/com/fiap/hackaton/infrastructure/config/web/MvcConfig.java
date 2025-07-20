package com.fiap.hackaton.infrastructure.config.web;

import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.coletaEquipamento.gateway.ColetaEquipamentoGateway;
import com.fiap.hackaton.entity.coletaInsumo.gateway.ColetaInsumoGateway;
import com.fiap.hackaton.entity.descarteEquipamento.gateway.DescarteEquipamentoGateway;
import com.fiap.hackaton.entity.entregaEquipamento.gateway.EntregaEquipamentoGateway;
import com.fiap.hackaton.entity.entregaInsumo.gateway.EntregaInsumoGateway;
import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.infrastructure.colaborador.gateway.ColaboradorDatabaseGateway;
import com.fiap.hackaton.infrastructure.coletaEquipamento.gateway.ColetaEquipamentoDatabaseGateway;
import com.fiap.hackaton.infrastructure.coletaInsumo.gateway.ColetaInsumoDatabaseGateway;
import com.fiap.hackaton.infrastructure.config.db.repository.*;
import com.fiap.hackaton.infrastructure.descarteEquipamento.gateway.DescarteEquipamentoDatabaseGateway;
import com.fiap.hackaton.infrastructure.entregaEquipamento.gateway.EntregaEquipamentoDatabaseGateway;
import com.fiap.hackaton.infrastructure.entregaInsumo.gateway.EntregaInsumoDatabaseGateway;
import com.fiap.hackaton.infrastructure.equipamento.gateway.EquipamentoDatabaseGateway;
import com.fiap.hackaton.infrastructure.estoqueEquipamento.gateway.EstoqueEquipamentoDatabaseGateway;
import com.fiap.hackaton.infrastructure.estoqueInsumo.gateway.EstoqueInsumoDatabaseGateway;
import com.fiap.hackaton.infrastructure.hospital.gateway.HospitalDatabaseGateway;
import com.fiap.hackaton.infrastructure.insumo.gateway.InsumoDatabaseGateway;
import com.fiap.hackaton.infrastructure.leito.gateway.LeitoDatabaseGateway;
import com.fiap.hackaton.infrastructure.paciente.gateway.PacienteDatabaseGateway;
import com.fiap.hackaton.usecase.colaborador.CreateColaboradorUseCase;
import com.fiap.hackaton.usecase.colaborador.GetColaboradorUseCase;
import com.fiap.hackaton.usecase.colaborador.SearchColaboradorUseCase;
import com.fiap.hackaton.usecase.colaborador.UpdateColaboradorUseCase;
import com.fiap.hackaton.usecase.coletaEquipamento.CreateColetaEquipamentoUseCase;
import com.fiap.hackaton.usecase.coletaEquipamento.GetColetaEquipamentoUseCase;
import com.fiap.hackaton.usecase.coletaEquipamento.SearchColetaEquipamentoUseCase;
import com.fiap.hackaton.usecase.coletaInsumo.CreateColetaInsumoUseCase;
import com.fiap.hackaton.usecase.coletaInsumo.GetColetaInsumoUseCase;
import com.fiap.hackaton.usecase.coletaInsumo.SearchColetaInsumoUseCase;
import com.fiap.hackaton.usecase.descarteEquipamento.CreateDescarteEquipamentoUseCase;
import com.fiap.hackaton.usecase.descarteEquipamento.GetDescarteEquipamentoUseCase;
import com.fiap.hackaton.usecase.descarteEquipamento.SearchDescarteEquipamentoUseCase;
import com.fiap.hackaton.usecase.entregaEquipamento.CreateEntregaEquipamentoUseCase;
import com.fiap.hackaton.usecase.entregaEquipamento.GetEntregaEquipamentoUseCase;
import com.fiap.hackaton.usecase.entregaEquipamento.SearchEntregaEquipamentoUseCase;
import com.fiap.hackaton.usecase.entregaInsumo.CreateEntregaInsumoUseCase;
import com.fiap.hackaton.usecase.entregaInsumo.GetEntregaInsumoUseCase;
import com.fiap.hackaton.usecase.entregaInsumo.SearchEntregaInsumoUseCase;
import com.fiap.hackaton.usecase.equipamento.*;
import com.fiap.hackaton.usecase.estoqueEquipamento.*;
import com.fiap.hackaton.usecase.estoqueInsumo.*;
import com.fiap.hackaton.usecase.hospital.*;
import com.fiap.hackaton.usecase.insumo.*;
import com.fiap.hackaton.usecase.leito.*;
import com.fiap.hackaton.usecase.paciente.CreatePacienteUseCase;
import com.fiap.hackaton.usecase.paciente.GetPacienteUseCase;
import com.fiap.hackaton.usecase.paciente.SearchPacienteUseCase;
import com.fiap.hackaton.usecase.paciente.UpdatePacienteUseCase;
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

    @Bean
    public CreateColaboradorUseCase createColaboradorUseCase(ColaboradorRepository colaboradorRepository) {
        ColaboradorGateway colaboradorGateway = new ColaboradorDatabaseGateway(colaboradorRepository);
        return new CreateColaboradorUseCase(colaboradorGateway);
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
    public UpdateColaboradorUseCase updateColaboradorUseCase(ColaboradorRepository colaboradorRepository) {
        ColaboradorGateway colaboradorGateway = new ColaboradorDatabaseGateway(colaboradorRepository);
        return new UpdateColaboradorUseCase(colaboradorGateway);
    }

    @Bean
    public CreateColetaEquipamentoUseCase createColetaEquipamentoUseCase(ColetaEquipamentoRepository coletaEquipamentoRepository) {
        ColetaEquipamentoGateway coletaEquipamentoGateway = new ColetaEquipamentoDatabaseGateway(coletaEquipamentoRepository);
        return new CreateColetaEquipamentoUseCase(coletaEquipamentoGateway);
    }

    @Bean
    public GetColetaEquipamentoUseCase getColetaEquipamentoUseCase(ColetaEquipamentoRepository coletaEquipamentoRepository) {
        ColetaEquipamentoGateway coletaEquipamentoGateway = new ColetaEquipamentoDatabaseGateway(coletaEquipamentoRepository);
        return new GetColetaEquipamentoUseCase(coletaEquipamentoGateway);
    }

    @Bean
    public SearchColetaEquipamentoUseCase searchColetaEquipamentoUseCase(ColetaEquipamentoRepository coletaEquipamentoRepository) {
        ColetaEquipamentoGateway coletaEquipamentoGateway = new ColetaEquipamentoDatabaseGateway(coletaEquipamentoRepository);
        return new SearchColetaEquipamentoUseCase(coletaEquipamentoGateway);
    }

    @Bean
    public CreateColetaInsumoUseCase createColetaInsumoUseCase(ColetaInsumoRepository coletaInsumoRepository) {
        ColetaInsumoGateway coletaInsumoGateway = new ColetaInsumoDatabaseGateway(coletaInsumoRepository);
        return new CreateColetaInsumoUseCase(coletaInsumoGateway);
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

    @Bean
    public CreateDescarteEquipamentoUseCase createDescarteEquipamentoUseCase(DescarteEquipamentoRepository descarteEquipamentoRepository) {
        DescarteEquipamentoGateway descarteEquipamentoGateway = new DescarteEquipamentoDatabaseGateway(descarteEquipamentoRepository);
        return new CreateDescarteEquipamentoUseCase(descarteEquipamentoGateway);
    }

    @Bean
    public GetDescarteEquipamentoUseCase getDescarteEquipamentoUseCase(DescarteEquipamentoRepository descarteEquipamentoRepository) {
        DescarteEquipamentoGateway descarteEquipamentoGateway = new DescarteEquipamentoDatabaseGateway(descarteEquipamentoRepository);
        return new GetDescarteEquipamentoUseCase(descarteEquipamentoGateway);
    }

    @Bean
    public SearchDescarteEquipamentoUseCase searchDescarteEquipamentoUseCase(DescarteEquipamentoRepository descarteEquipamentoRepository) {
        DescarteEquipamentoGateway descarteEquipamentoGateway = new DescarteEquipamentoDatabaseGateway(descarteEquipamentoRepository);
        return new SearchDescarteEquipamentoUseCase(descarteEquipamentoGateway);
    }

    // --- EntregaEquipamento ---
    @Bean
    public CreateEntregaEquipamentoUseCase createEntregaEquipamentoUseCase(EntregaEquipamentoRepository entregaEquipamentoRepository) {
        EntregaEquipamentoGateway entregaEquipamentoGateway = new EntregaEquipamentoDatabaseGateway(entregaEquipamentoRepository);
        return new CreateEntregaEquipamentoUseCase(entregaEquipamentoGateway);
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
    public CreateEntregaInsumoUseCase createEntregaInsumoUseCase(EntregaInsumoRepository entregaInsumoRepository) {
        EntregaInsumoGateway entregaInsumoGateway = new EntregaInsumoDatabaseGateway(entregaInsumoRepository);
        return new CreateEntregaInsumoUseCase(entregaInsumoGateway);
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
    public CreateEstoqueEquipamentoUseCase createEstoqueEquipamentoUseCase(EstoqueEquipamentoRepository estoqueEquipamentoRepository) {
        EstoqueEquipamentoGateway estoqueEquipamentoGateway = new EstoqueEquipamentoDatabaseGateway(estoqueEquipamentoRepository);
        return new CreateEstoqueEquipamentoUseCase(estoqueEquipamentoGateway);
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
    public UpdateEstoqueEquipamentoUseCase updateEstoqueEquipamentoUseCase(EstoqueEquipamentoRepository estoqueEquipamentoRepository) {
        EstoqueEquipamentoGateway estoqueEquipamentoGateway = new EstoqueEquipamentoDatabaseGateway(estoqueEquipamentoRepository);
        return new UpdateEstoqueEquipamentoUseCase(estoqueEquipamentoGateway);
    }

    // --- EstoqueInsumo ---
    @Bean
    public CreateEstoqueInsumoUseCase createEstoqueInsumoUseCase(EstoqueInsumoRepository estoqueInsumoRepository) {
        EstoqueInsumoGateway estoqueInsumoGateway = new EstoqueInsumoDatabaseGateway(estoqueInsumoRepository);
        return new CreateEstoqueInsumoUseCase(estoqueInsumoGateway);
    }

    @Bean
    public DeleteEstoqueInsumoUseCase deleteEstoqueInsumoUseCase(EstoqueInsumoRepository estoqueInsumoRepository) {
        EstoqueInsumoGateway estoqueInsumoGateway = new EstoqueInsumoDatabaseGateway(estoqueInsumoRepository);
        return new DeleteEstoqueInsumoUseCase(estoqueInsumoGateway);
    }

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

    @Bean
    public UpdateEstoqueInsumoUseCase updateEstoqueInsumoUseCase(EstoqueInsumoRepository estoqueInsumoRepository) {
        EstoqueInsumoGateway estoqueInsumoGateway = new EstoqueInsumoDatabaseGateway(estoqueInsumoRepository);
        return new UpdateEstoqueInsumoUseCase(estoqueInsumoGateway);
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
    public CreateLeitoUseCase createLeitoUseCase(LeitoRepository leitoRepository) {
        LeitoGateway leitoGateway = new LeitoDatabaseGateway(leitoRepository);
        return new CreateLeitoUseCase(leitoGateway);
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
    public UpdateLeitoUseCase updateLeitoUseCase(LeitoRepository leitoRepository) {
        LeitoGateway leitoGateway = new LeitoDatabaseGateway(leitoRepository);
        return new UpdateLeitoUseCase(leitoGateway);
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