package com.fiap.hackaton.entity.coletaEquipamento.exception;

public class ColetaEquipamentoNotFoundException extends Exception {

    public ColetaEquipamentoNotFoundException() {
        super("A coleta não foi encontrada!");
    }

}
