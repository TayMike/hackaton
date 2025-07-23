package com.fiap.hackaton.bdd.insumo;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateInsumoBDD {

    private Response response;
    private String insumoJson;

    @Dado("que foi comprado um Insumo - Criar")
    public void que_foi_comprado_um_insumo() {
        insumoJson = """
            {
                "nome": "Soro Fisiológico",
                "custo": 15.75,
                "quantidade": 100,
                "peso": 500,
                "validade": "2025-12-31",
                "marca": "Farmavida",
                "unidadeMedida": "ML"
            }
            """;
    }

    @Quando("o Insumo for cadastrado - Criar")
    public void o_insumo_for_cadastrado() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumoJson)
                .when()
                .post("/insumos");
    }

    @Então("o Insumo será salvo no sistema - Criar")
    public void o_insumo_sera_salvo_no_sistema() {
        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @E("o Insumo deve estar no formato esperado - Criar")
    public void o_insumo_deve_estar_no_formato_esperado() {
        response.then().body(matchesJsonSchemaInClasspath("schemas/insumo.schema.json"));
    }
}