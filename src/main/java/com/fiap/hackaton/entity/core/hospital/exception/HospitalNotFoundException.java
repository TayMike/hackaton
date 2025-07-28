package com.fiap.hackaton.entity.core.hospital.exception;

public class HospitalNotFoundException extends Exception {

    public HospitalNotFoundException(String id) {
        super(id);
    }
}
