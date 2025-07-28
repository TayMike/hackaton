package com.fiap.hackaton.bdd.equipamentoDominio.equipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class SearchEquipamentoBDD {

    private Response response;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existem vários equipamentos cadastrados - Search")
    public void que_existem_varios_equipamentos_cadastrados() {

        String equipamentoJson = String.format("""
                {
                  "nome": "Marca D",
                  "custo": "1500.00",
                  "tempoGarantia": "%s",
                  "proximaManutencaoPreventiva": "%s",
                  "numeroSerie": "SN124256789",
                  "marca": "Marca F",
                  "descarte": null
                }
                """, dataFormatada, dataFormatada);

        Response equipamentoResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson)
                .when()
                .post("/equipamentos");

        equipamentoResponse.then().statusCode(HttpStatus.CREATED.value());
    }

    @Quando("os equipamentos forem buscados - Search")
    public void os_equipamentos_forem_buscados() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/equipamentos");
    }

    @Então("os equipamentos serão retornados no sistema - Search")
    public void os_equipamentos_serao_retornados_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}