package com.fiap.hackaton.bdd.equipamento;

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

public class CreateEquipamentoBDD {

    @Autowired
    private HospitalRepository hospitalRepository;
    private Response response;
    private String equipamentoJson;
    private String hospitalId;

    @Dado("que foi construido um hospital e um equipamento - Criar")
    public void que_foi_construido_um_hospital_e_um_equipamento() {
        String hospitalJson = """
                {
                    "nome": "Hospital Central",
                    "cnpj": "12345678000401",
                    "colaboradores": [],
                    "cep": "12345678",
                    "numero": 100,
                    "quantidadeLeitoAtual": 10,
                    "quantidadeLeitoMaximo": 20
                }
                """;

        Response hospitalResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(hospitalJson)
                .when()
                .post("/hospitais");

        hospitalId = hospitalResponse.jsonPath().getString("id");

        equipamentoJson = String.format("""
                {
                    "nome": "Equipamento X",
                    "custo": 1000.0,
                    "tempoGarantia": "2026-07-23T00:00:00Z",
                    "proximaManutencaoPreventiva": "2025-12-23T00:00:00Z",
                    "marca": "Marca Y",
                    "hospital": "%s"
                }
                """, hospitalId);
    }

    @Quando("o equipamento for cadastrado - Criar")
    public void o_equipamento_for_cadastrado() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson)
                .when()
                .post("/equipamentos");
    }

    @Então("o equipamento será salvo no sistema - Criar")
    public void o_equipamento_sera_salvo_no_sistema() {
        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @E("o equipamento deve estar no formato esperado - Criar")
    public void o_equipamento_deve_estar_no_formato_esperado() {
        response.then().body(matchesJsonSchemaInClasspath("schemas/equipamento.schema.json"));
    }
}