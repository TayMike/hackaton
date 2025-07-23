package com.fiap.hackaton.bdd.insumo;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class SearchInsumoBDD {

    private Response response;

    @Dado("que existem vários insumos cadastrados - Search")
    public void que_existem_varios_insumos_cadastrados() {

        String insumoJson1 = """
            {
                "nome": "Máscara Cirúrgica",
                "custo": 2.50,
                "quantidade": 1000,
                "peso": 20,
                "validade": "2026-12-31",
                "marca": "ProtecMed",
                "unidadeMedida": "MG"
            }
            """;

        String insumoJson2 = """
            {
                "nome": "Soro Fisiológico",
                "custo": 15.75,
                "quantidade": 500,
                "peso": 500,
                "validade": "2025-12-31",
                "marca": "Farmavida",
                "unidadeMedida": "ML"
            }
            """;

        Response response1 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumoJson1)
                .when()
                .post("/insumos");

        response1.then().statusCode(HttpStatus.CREATED.value());

        Response response2 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumoJson2)
                .when()
                .post("/insumos");

        response2.then().statusCode(HttpStatus.CREATED.value());
    }

    @Quando("os insumos forem buscados - Search")
    public void os_insumos_foream_buscados() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/insumos");
    }

    @Então("os insumos serão retornados no sistema - Search")
    public void os_insumos_serao_retornados_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}