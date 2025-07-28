package com.fiap.hackaton.entity.core.paciente.exception;

public class PacienteNotFoundException extends Exception {

    public PacienteNotFoundException(String id) {
        super(id);
    }
}
