package com.fiap.hackaton.bdd.equipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class SearchEquipamentoBDD {

    private Response response;

    @Dado("que existem vários equipamentos cadastrados - Search")
    public void que_existem_varios_equipamentos_cadastrados() {
        String hospitalJson = """
                {
                    "nome": "Hospital Central",
                    "cnpj": "12345678000404",
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

        String hospitalId = hospitalResponse.jsonPath().getString("id");

        String equipamentoJson = String.format("""
                {
                    "nome": "Equipamento X",
                    "custo": 1000.0,
                    "tempoGarantia": "2026-07-23T00:00:00Z",
                    "proximaManutencaoPreventiva": "2025-12-23T00:00:00Z",
                    "marca": "Marca Y",
                    "hospital": "%s"
                }
                """, hospitalId);

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