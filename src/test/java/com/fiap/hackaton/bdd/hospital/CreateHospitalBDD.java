package com.fiap.hackaton.bdd.hospital;

import com.fiap.hackaton.infrastructure.config.db.repository.HospitalRepository;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateHospitalBDD {

    @Autowired
    private HospitalRepository hospitalRepository;
    private Response response;
    private String hospitalJson;

    @Dado("que foi construido um hospital - Criar")
    public void que_foi_construido_um_hospital() {
        hospitalJson = """
                {
                    "colaboradores": [],
                    "cep": "12345678",
                    "numero": 100,
                    "quantidadeLeitoAtual": 10,
                    "quantidadeLeitoMaximo": 20
                }
                """;
    }

    @Quando("o hospital for cadastrado - Criar")
    public void o_hospital_for_cadastrado() {

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(hospitalJson)
                .when()
                .post("/hospitais");
    }

    @Então("o hospital será salvo no sistema - Criar")
    public void o_hospital_sera_salvo_no_sistema() {
        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @E("o hospital deve estar no formato esperado - Criar")
    public void o_hospital_deve_estar_no_formato_esperado() {
        response.then().body(matchesJsonSchemaInClasspath("schemas/hospital.schema.json"));
    }

}
