package com.fiap.hackaton.bdd.equipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class UpdateEquipamentoBDD {

    private Response response;
    private String equipamentoId;
    private String equipamentoJsonPut;
    private String hospitalId;

    @Dado("que existe um hospital e um equipamento cadastrados - Update")
    public void que_existe_um_hospital_e_um_equipamento_cadastrados() {
        String hospitalJson = """
                {
                    "nome": "Hospital Central",
                    "cnpj": "12345678000405",
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

        equipamentoJsonPut = String.format("""
                {
                    "nome": "Equipamento Atualizado",
                    "custo": 1200.0,
                    "tempoGarantia": "2027-07-23T00:00:00Z",
                    "proximaManutencaoPreventiva": "2026-12-23T00:00:00Z",
                    "marca": "Marca Atualizada",
                    "hospital": "%s"
                }
                """, hospitalId);
    }

    @Quando("o equipamento for modificado pelo ID - Update")
    public void o_equipamento_for_modificado_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJsonPut)
                .when()
                .put("/equipamentos/" + equipamentoId);
    }

    @Então("o equipamento modificado será retornado no sistema - Update")
    public void o_equipamento_modificado_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}