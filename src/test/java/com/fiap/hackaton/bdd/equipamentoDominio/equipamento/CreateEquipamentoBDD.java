package com.fiap.hackaton.bdd.equipamentoDominio.equipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateEquipamentoBDD {

    private Response response;
    private String equipamentoJson;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que foi construido um hospital e um equipamento - Criar")
    public void que_foi_construido_um_hospital_e_um_equipamento() {

        equipamentoJson = String.format("""
                {
                  "nome": "Marca X",
                  "custo": "1500.00",
                  "tempoGarantia": "%s",
                  "proximaManutencaoPreventiva": "%s",
                  "numeroSerie": "SN124256789",
                  "marca": "Marca Y",
                  "descarte": null
                }
                """, dataFormatada, dataFormatada);
    }

    @Quando("o equipamento for cadastrado - Criar")
    public void o_equipamento_for_cadastrado() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson)
                .when()
                .post("/equipamentos");
    }

    @Então("o equipamento será salvo no sistema - Criar")
    public void o_equipamento_sera_salvo_no_sistema() {
        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @E("o equipamento deve estar no formato esperado - Criar")
    public void o_equipamento_deve_estar_no_formato_esperado() {
        response.then().body(matchesJsonSchemaInClasspath("schemas/equipamento.schema.json"));
    }
}