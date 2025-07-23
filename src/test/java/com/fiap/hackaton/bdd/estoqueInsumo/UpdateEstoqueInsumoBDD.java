package com.fiap.hackaton.bdd.estoqueInsumo;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class UpdateEstoqueInsumoBDD {

    private Response response;
    private String estoqueInsumoId;
    private String estoqueInsumoJsonPut;

    @Dado("que existe um estoque de insumo cadastrado - Update")
    public void que_existe_um_estoque_de_insumo_cadastrado() {

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

        String estoqueInsumoJson = String.format("""
                {
                    "itens": ["%s", "%s"],
                    "quantidades": [5, 10],
                    "hospital": "%s"
                }
                """, insumo1Id, insumo2Id, hospitalId);

        Response estoqueResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(estoqueInsumoJson)
                .when()
                .post("/estoqueInsumos");

        estoqueInsumoId = estoqueResponse.jsonPath().getString("id");

        estoqueInsumoJsonPut = String.format("""
                {
                    "itens": ["%s"],
                    "quantidades": [20],
                    "hospital": "%s"
                }
                """, insumo1Id, hospitalId);
    }

    @Quando("o estoque de insumo for modificado pelo ID - Update")
    public void o_estoque_de_insumo_for_modificado_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(estoqueInsumoJsonPut)
                .when()
                .put("/estoqueInsumos/" + estoqueInsumoId);
    }

    @Então("o estoque de insumo modificado será retornado no sistema - Update")
    public void o_estoque_de_insumo_modificado_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }

}