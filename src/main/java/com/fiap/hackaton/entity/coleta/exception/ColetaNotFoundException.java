package com.fiap.hackaton.entity.coleta.exception;

public class ColetaNotFoundException extends Exception {

    public ColetaNotFoundException() {
        super("A coleta não foi encontrada!");
    }
}
