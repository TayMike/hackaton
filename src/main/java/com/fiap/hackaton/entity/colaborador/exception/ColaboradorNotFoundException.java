package com.fiap.hackaton.entity.colaborador.exception;

public class ColaboradorNotFoundException extends Exception {

    public ColaboradorNotFoundException() {
        super("O colaborador não foi encontrado!");
    }
}
