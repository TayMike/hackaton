package com.fiap.hackaton.entity.hospital.exception;

public class HospitalNotFoundException extends Exception {

    public HospitalNotFoundException() {
        super("O hospital não foi encontrado!");
    }
}
