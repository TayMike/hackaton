package com.fiap.hackaton.bdd.estoqueEquipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.ArrayList;
import java.util.List;

public class CreateEstoqueEquipamentoBDD {

    private Response response;
    private String estoqueEquipamentoJson;
    private String hospitalId;
    private List<String> equipamentoIds = new ArrayList<>();

    @Dado("que existe um hospital cadastrado para o estoque de equipamento - Create")
    public void que_existe_um_hospital_cadastrado_para_o_estoque_de_equipamento() {
        String hospitalJson = """
                {
                    "nome": "Hospital Teste",
                    "cnpj": "12345678000100",
                    "colaboradores": [],
                    "cep": "12345678",
                    "numero": 123,
                    "quantidadeLeitoAtual": 10,
                    "quantidadeLeitoMaximo": 50
                }
                """;

        Response hospitalResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(hospitalJson)
                .when()
                .post("/hospitais");

        hospitalId = hospitalResponse.jsonPath().getString("id");
    }

    @E("que existem equipamentos cadastrados para o estoque - Create")
    public void que_existem_equipamentos_cadastrados_para_o_estoque() {

        String equipamentoJson1 = String.format("""
                {
                    "nome": "Equipamento Teste 1",
                    "custo": 1000.00,
                    "tempoGarantia": "2026-12-31T00:00:00Z",
                    "proximaManutencaoPreventiva": "2025-12-31T00:00:00Z",
                    "marca": "Marca A",
                    "hospital": "%s"
                }
                """, hospitalId);

        Response equipamentoResponse1 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson1)
                .when()
                .post("/equipamentos");

        equipamentoIds.add(equipamentoResponse1.jsonPath().getString("id"));

        String equipamentoJson2 = String.format("""
                {
                    "nome": "Equipamento Teste 2",
                    "custo": 2000.00,
                    "tempoGarantia": "2027-12-31T00:00:00Z",
                    "proximaManutencaoPreventiva": "2026-12-31T00:00:00Z",
                    "marca": "Marca B",
                    "hospital": "%s"
                }
                """, hospitalId);

        Response equipamentoResponse2 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson2)
                .when()
                .post("/equipamentos");

        equipamentoIds.add(equipamentoResponse2.jsonPath().getString("id"));
    }

    @Dado("que foi construído um estoque de equipamento para criação - Create")
    public void que_foi_construido_um_estoque_de_equipamento_para_criacao() {

        String itensJsonArray = equipamentoIds.stream()
                .map(id -> "\"" + id + "\"")
                .reduce((a, b) -> a + "," + b)
                .orElse("");

        estoqueEquipamentoJson = String.format("""
                {
                    "itens": [%s],
                    "quantidades": [5, 10],
                    "hospital": "%s"
                }
                """, itensJsonArray, hospitalId);
    }

    @Quando("o estoque de equipamento for cadastrado - Create")
    public void o_estoque_de_equipamento_for_cadastrado() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(estoqueEquipamentoJson)
                .when()
                .post("/estoque-equipamentos");
    }

    @Então("o estoque de equipamento será salvo no sistema - Create")
    public void o_estoque_de_equipamento_sera_salvo_no_sistema() {
        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @E("o estoque de equipamento deve estar no formato esperado - Create")
    public void o_estoque_de_equipamento_deve_estar_no_formato_esperado() {
        response.then().body(matchesJsonSchemaInClasspath("schemas/estoque-equipamento.schema.json"));
    }

}