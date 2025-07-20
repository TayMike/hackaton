package com.fiap.hackaton.infrastructure.hospital.controller;

import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.hospital.dto.HospitalPublicData;
import com.fiap.hackaton.usecase.hospital.SearchHospitalUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchHospitalController {

    private final SearchHospitalUseCase searchHospitalUseCase;

    public SearchHospitalController(SearchHospitalUseCase searchHospitalUseCase) {
        this.searchHospitalUseCase = searchHospitalUseCase;
    }

    @GetMapping("/hospitais")
    @ResponseStatus(HttpStatus.OK)
    public List<HospitalPublicData> searchHospital() {
        List<Hospital> hospitals = this.searchHospitalUseCase.execute();
        return hospitals.stream().map(HospitalPublicData::new).toList();
    }

}
