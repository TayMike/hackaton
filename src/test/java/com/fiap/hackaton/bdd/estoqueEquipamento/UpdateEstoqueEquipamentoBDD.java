package com.fiap.hackaton.bdd.estoqueEquipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

public class UpdateEstoqueEquipamentoBDD {

    private Response response;
    private String estoqueEquipamentoId;
    private String hospitalId;
    private List<String> equipamentoIds = new ArrayList<>();
    private String estoqueEquipamentoJsonPut;

    @Dado("que existe um estoque de equipamento cadastrado para update - Update")
    public void que_existe_um_estoque_de_equipamento_cadastrado_para_update() {

        String hospitalJson = """
                {
                    "nome": "Hospital Update",
                    "cnpj": "12345678000133",
                    "colaboradores": [],
                    "cep": "56781234",
                    "numero": 400,
                    "quantidadeLeitoAtual": 25,
                    "quantidadeLeitoMaximo": 80
                }
                """;

        Response hospitalResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(hospitalJson)
                .when()
                .post("/hospitais");

        hospitalId = hospitalResponse.jsonPath().getString("id");

        String equipamentoJson1 = String.format("""
                {
                    "nome": "Equipamento Update 1",
                    "custo": 1800.00,
                    "tempoGarantia": "2028-12-31T00:00:00Z",
                    "proximaManutencaoPreventiva": "2027-12-31T00:00:00Z",
                    "marca": "Marca U1",
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
                    "nome": "Equipamento Update 2",
                    "custo": 2200.00,
                    "tempoGarantia": "2029-12-31T00:00:00Z",
                    "proximaManutencaoPreventiva": "2028-12-31T00:00:00Z",
                    "marca": "Marca U2",
                    "hospital": "%s"
                }
                """, hospitalId);

        Response equipamentoResponse2 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson2)
                .when()
                .post("/equipamentos");

        equipamentoIds.add(equipamentoResponse2.jsonPath().getString("id"));

        String itensJsonArray = equipamentoIds.stream()
                .map(id -> "\"" + id + "\"")
                .reduce((a, b) -> a + "," + b)
                .orElse("");

        String estoqueJson = String.format("""
                {
                    "itens": [%s],
                    "quantidades": [3, 6],
                    "hospital": "%s"
                }
                """, itensJsonArray, hospitalId);

        Response estoqueResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(estoqueJson)
                .when()
                .post("/estoque-equipamentos");

        estoqueEquipamentoId = estoqueResponse.jsonPath().getString("id");

        estoqueEquipamentoJsonPut = String.format("""
                {
                    "itens": ["%s"],
                    "quantidades": [9],
                    "hospital": "%s"
                }
                """, equipamentoIds.get(0), hospitalId);
    }

    @Quando("o estoque de equipamento for modificado pelo ID - Update")
    public void o_estoque_de_equipamento_for_modificado_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(estoqueEquipamentoJsonPut)
                .when()
                .put("/estoque-equipamentos/" + estoqueEquipamentoId);
    }

    @Então("o estoque de equipamento modificado será retornado no sistema - Update")
    public void o_estoque_de_equipamento_modificado_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}