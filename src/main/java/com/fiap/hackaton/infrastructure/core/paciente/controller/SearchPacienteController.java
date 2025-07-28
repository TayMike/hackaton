package com.fiap.hackaton.infrastructure.core.paciente.controller;

import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import com.fiap.hackaton.infrastructure.core.paciente.dto.PacientePublicData;
import com.fiap.hackaton.usecase.core.paciente.SearchPacienteUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchPacienteController {

    private final SearchPacienteUseCase searchPacienteUseCase;

    public SearchPacienteController(SearchPacienteUseCase searchPacienteUseCase) {
        this.searchPacienteUseCase = searchPacienteUseCase;
    }

    @GetMapping("/pacientes")
    @ResponseStatus(HttpStatus.OK)
    public List<PacientePublicData> searchPaciente() {
        List<Paciente> pacientes = this.searchPacienteUseCase.execute();
        return pacientes.stream().map(PacientePublicData::new).toList();
    }

}
