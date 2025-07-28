package com.fiap.hackaton.bdd.insumoDominio.insumo;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class SearchInsumoBDD {

    private Response response;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existem vários insumos cadastrados - Search")
    public void que_existem_varios_insumos_cadastrados() {

        String insumoJson1 = String.format("""
            {
                "nome": "Máscara Cirúrgica",
                "custo": 2.50,
                "quantidade": 1000,
                "peso": 20,
                "validade": "%s",
                "marca": "ProtecMed",
                "unidadeMedida": "MG"
            }
            """, dataFormatada);

        String insumoJson2 = String.format("""
            {
                "nome": "Soro Fisiológico",
                "custo": 15.75,
                "quantidade": 500,
                "peso": 500,
                "validade": "%s",
                "marca": "Farmavida",
                "unidadeMedida": "ML"
            }
            """, dataFormatada);

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