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

public class GetEquipamentoBDD {

    private Response response;
    private String equipamentoId;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um hospital e um equipamento cadastrados - Get")
    public void que_existe_um_hospital_e_um_equipamento_cadastrados() {

        String equipamentoJson = String.format("""
                {
                  "nome": "Marca M",
                  "custo": "1500.00",
                  "tempoGarantia": "%s",
                  "proximaManutencaoPreventiva": "%s",
                  "numeroSerie": "SN194356789",
                  "marca": "Marca N",
                  "descarte": null
                }
                """, dataFormatada, dataFormatada);

        Response equipamentoResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson)
                .when()
                .post("/equipamentos");

        equipamentoId = equipamentoResponse.jsonPath().getString("id");
    }

    @Quando("um equipamento for buscado pelo ID - Get")
    public void um_equipamento_for_buscado_pelo_ID() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/equipamentos/" + equipamentoId);
    }

    @Então("o equipamento será retornado no sistema - Get")
    public void o_equipamento_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}