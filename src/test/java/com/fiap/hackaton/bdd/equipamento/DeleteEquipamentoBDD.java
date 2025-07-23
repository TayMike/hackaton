package com.fiap.hackaton.bdd.equipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class DeleteEquipamentoBDD {

    private Response response;
    private String equipamentoId;
    private String hospitalId;

    @Dado("que existe um hospital e um equipamento cadastrados - Delete")
    public void que_existe_um_hospital_e_um_equipamento_cadastrados() {
        String hospitalJson = """
                {
                    "nome": "Hospital Central",
                    "cnpj": "12345678000406",
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

        hospitalId = hospitalResponse.jsonPath().getString("id");

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

        equipamentoId = equipamentoResponse.jsonPath().getString("id");
    }

    @Quando("o equipamento for deletado pelo ID - Delete")
    public void o_equipamento_for_deletado_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/equipamentos/" + equipamentoId);
    }

    @Então("o equipamento deletado não será retornado no sistema - Delete")
    public void o_equipamento_deletado_nao_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}