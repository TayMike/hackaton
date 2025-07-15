package com.fiap.hackaton.entity.paciente.gateway;

import com.fiap.hackaton.entity.paciente.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteGateway {
    Paciente save(Paciente paciente);
    Optional<Paciente> findById(Long id);
    List<Paciente> findAll();
    void deleteById(Long id);
}