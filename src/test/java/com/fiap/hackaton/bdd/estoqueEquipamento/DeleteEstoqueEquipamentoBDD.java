package com.fiap.hackaton.bdd.estoqueEquipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class DeleteEstoqueEquipamentoBDD {

    private Response response;
    private String estoqueEquipamentoId;
    private String hospitalId;
    private String equipamentoId;

    @Dado("que existe um estoque de equipamento cadastrado para delete - Delete")
    public void que_existe_um_estoque_de_equipamento_cadastrado_para_delete() {

        String hospitalJson = """
                {
                    "nome": "Hospital Delete",
                    "cnpj": "12345678000144",
                    "colaboradores": [],
                    "cep": "43218765",
                    "numero": 500,
                    "quantidadeLeitoAtual": 30,
                    "quantidadeLeitoMaximo": 90
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
                    "nome": "Equipamento Delete",
                    "custo": 3000.00,
                    "tempoGarantia": "2030-12-31T00:00:00Z",
                    "proximaManutencaoPreventiva": "2029-12-31T00:00:00Z",
                    "marca": "Marca D",
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
                    "quantidades": [20],
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

    @Quando("o estoque de equipamento for deletado pelo ID - Delete")
    public void o_estoque_de_equipamento_for_deletado_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/estoque-equipamentos/" + estoqueEquipamentoId);
    }

    @Então("o estoque de equipamento deletado não será retornado no sistema - Delete")
    public void o_estoque_de_equipamento_deletado_nao_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }

}