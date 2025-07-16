package com.fiap.hackaton.entity.paciente.exception;

public class PacienteNotFoundException extends Exception {

    public PacienteNotFoundException() {
        super("O paciente não foi encontrado!");
    }
}
