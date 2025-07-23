package com.fiap.hackaton.bdd.estoqueEquipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class GetEstoqueEquipamentoBDD {

    private Response response;
    private String estoqueEquipamentoId;
    private String hospitalId;
    private String equipamentoId;

    @Dado("que existe um estoque de equipamento cadastrado - Get")
    public void que_existe_um_estoque_de_equipamento_cadastrado() {

        String hospitalJson = """
                {
                    "nome": "Hospital para Get",
                    "cnpj": "12345678000111",
                    "colaboradores": [],
                    "cep": "87654321",
                    "numero": 200,
                    "quantidadeLeitoAtual": 15,
                    "quantidadeLeitoMaximo": 60
                }
                """;

        Response hospitalResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(hospitalJson)
                .when()
                .post("/hospitais");

        hospitalId = hospitalResponse.jsonPath().getString("id");

        String equipamentoJson = String.format("""
                {
                    "nome": "Equipamento para Get",
                    "custo": 1500.00,
                    "tempoGarantia": "2026-11-30T00:00:00Z",
                    "proximaManutencaoPreventiva": "2025-11-30T00:00:00Z",
                    "marca": "Marca G",
                    "hospital": "%s"
                }
                """, hospitalId);

        Response equipamentoResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson)
                .when()
                .post("/equipamentos");

        equipamentoId = equipamentoResponse.jsonPath().getString("id");

        String estoqueJson = String.format("""
                {
                    "itens": ["%s"],
                    "quantidades": [7],
                    "hospital": "%s"
                }
                """, equipamentoId, hospitalId);

        Response estoqueResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(estoqueJson)
                .when()
                .post("/estoque-equipamentos");

        estoqueEquipamentoId = estoqueResponse.jsonPath().getString("id");
    }

    @Quando("um estoque de equipamento for buscado pelo ID - Get")
    public void um_estoque_de_equipamento_for_buscado_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/estoque-equipamentos/" + estoqueEquipamentoId);
    }

    @Então("o estoque de equipamento será retornado no sistema - Get")
    public void o_estoque_de_equipamento_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }

}