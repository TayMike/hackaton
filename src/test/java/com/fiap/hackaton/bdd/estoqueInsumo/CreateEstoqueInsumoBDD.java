package com.fiap.hackaton.bdd.estoqueInsumo;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateEstoqueInsumoBDD {

    private Response response;
    private String estoqueInsumoJson;

    @Dado("que foi construido um estoque de insumo - Criar")
    public void que_foi_construido_um_estoque_de_insumo() {
        String hospitalJson = """
                {
                    "nome": "Hospital Central",
                    "cnpj": "12345678000195",
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

        estoqueInsumoJson = String.format("""
                {
                    "itens": ["%s", "%s"],
                    "quantidades": [5, 10],
                    "hospital": "%s"
                }
                """, insumo1Id, insumo2Id, hospitalId);
    }

    @Quando("o estoque de insumo for cadastrado - Criar")
    public void o_estoque_de_insumo_for_cadastrado() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(estoqueInsumoJson)
                .when()
                .post("/estoqueInsumos");
    }

    @Então("o estoque de insumo será salvo no sistema - Criar")
    public void o_estoque_de_insumo_sera_salvo_no_sistema() {
        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @E("o estoque de insumo deve estar no formato esperado - Criar")
    public void o_estoque_de_insumo_deve_estar_no_formato_esperado() {
        response.then().body(matchesJsonSchemaInClasspath("schemas/estoque-insumo.schema.json"));
    }
}