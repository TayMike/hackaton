package com.fiap.hackaton.bdd.estoqueInsumo;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class SearchEstoqueInsumoBDD {

    private Response response;

    @Dado("que existem vários estoques de insumo cadastrados - Search")
    public void que_existem_varios_estoques_de_insumo_cadastrados() {

        String hospitalJson = """
                {
                    "nome": "Hospital Central",
                    "cnpj": "12345678000193",
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

        String insumo1Json = """
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

        String insumo2Json = """
                {
                    "nome": "Algodão",
                    "custo": 3.25,
                    "quantidade": 250,
                    "peso": 300,
                    "validade": "2026-06-30",
                    "marca": "Algodão Puro",
                    "unidadeMedida": "MG"
                }
                """;

        Response insumo1Response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumo1Json)
                .when()
                .post("/insumos");

        Response insumo2Response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumo2Json)
                .when()
                .post("/insumos");

        String insumo1Id = insumo1Response.jsonPath().getString("id");
        String insumo2Id = insumo2Response.jsonPath().getString("id");

        String estoqueInsumoJson1 = String.format("""
                {
                    "itens": ["%s", "%s"],
                    "quantidades": [5, 10],
                    "hospital": "%s"
                }
                """, insumo1Id, insumo2Id, hospitalId);

        String estoqueInsumoJson2 = String.format("""
                {
                    "itens": ["%s"],
                    "quantidades": [15],
                    "hospital": "%s"
                }
                """, insumo1Id, hospitalId);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(estoqueInsumoJson1)
                .when()
                .post("/estoqueInsumos")
                .then()
                .statusCode(HttpStatus.CREATED.value());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(estoqueInsumoJson2)
                .when()
                .post("/estoqueInsumos")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Quando("os estoques de insumo forem buscados - Search")
    public void os_estoques_de_insumo_forem_buscados() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/estoqueInsumos");
    }

    @Então("os estoques de insumo serão retornados no sistema - Search")
    public void os_estoques_de_insumo_serao_retornados_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }

}