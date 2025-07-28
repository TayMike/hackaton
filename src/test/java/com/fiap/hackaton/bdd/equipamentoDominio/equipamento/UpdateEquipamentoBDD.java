package com.fiap.hackaton.bdd.equipamentoDominio.equipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class UpdateEquipamentoBDD {

    private Response response;
    private String equipamentoId;
    private String equipamentoJsonPut;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um hospital e um equipamento cadastrados - Update")
    public void que_existe_um_hospital_e_um_equipamento_cadastrados() {

        String equipamentoJson = String.format("""
                {
                  "nome": "Marca G",
                  "custo": "1500.00",
                  "tempoGarantia": "%s",
                  "proximaManutencaoPreventiva": "%s",
                  "numeroSerie": "SN291256789",
                  "marca": "Marca H",
                  "descarte": null
                }
                """, dataFormatada, dataFormatada);

        Response equipamentoResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson)
                .when()
                .post("/equipamentos");

        equipamentoId = equipamentoResponse.jsonPath().getString("id");

        equipamentoJsonPut = String.format("""
                {
                  "nome": "Marca J",
                  "custo": "1500.00",
                  "tempoGarantia": "%s",
                  "proximaManutencaoPreventiva": "%s",
                  "marca": "Marca K",
                  "descarte": "%s"
                }
                """, dataFormatada, dataFormatada, dataFormatada);
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