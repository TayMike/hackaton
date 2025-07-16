package com.fiap.hackaton.entity.entrega.exception;

public class EntregaNotFoundException extends Exception {

    public EntregaNotFoundException() {
        super("A entrega não foi encontrada!");
    }
}
