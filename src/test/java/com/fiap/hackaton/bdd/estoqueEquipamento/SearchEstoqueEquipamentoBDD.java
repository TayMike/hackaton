package com.fiap.hackaton.bdd.estoqueEquipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class SearchEstoqueEquipamentoBDD {

    private Response response;
    private String hospitalId;
    private String equipamentoId;

    @Dado("que existem vários estoques de equipamentos cadastrados - Search")
    public void que_existem_varios_estoques_de_equipamentos_cadastrados() {

        String hospitalJson = """
                {
                    "nome": "Hospital para Search",
                    "cnpj": "12345678000122",
                    "colaboradores": [],
                    "cep": "12344321",
                    "numero": 300,
                    "quantidadeLeitoAtual": 20,
                    "quantidadeLeitoMaximo": 70
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
                    "nome": "Equipamento para Search",
                    "custo": 2500.00,
                    "tempoGarantia": "2027-10-30T00:00:00Z",
                    "proximaManutencaoPreventiva": "2026-10-30T00:00:00Z",
                    "marca": "Marca S",
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
                    "quantidades": [12],
                    "hospital": "%s"
                }
                """, equipamentoId, hospitalId);

        Response estoqueResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(estoqueJson)
                .when()
                .post("/estoque-equipamentos");

        estoqueResponse.then().statusCode(HttpStatus.CREATED.value());
    }

    @Quando("os estoques de equipamentos forem buscados - Search")
    public void os_estoques_de_equipamentos_forem_buscados() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/estoque-equipamentos");
    }

    @Então("os estoques de equipamentos serão retornados no sistema - Search")
    public void os_estoques_de_equipamentos_serao_retornados_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }

}