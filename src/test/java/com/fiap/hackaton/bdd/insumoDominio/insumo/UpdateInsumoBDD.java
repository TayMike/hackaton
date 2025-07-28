package com.fiap.hackaton.bdd.insumoDominio.insumo;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class UpdateInsumoBDD {

    private Response response;
    private String insumoId;
    private String insumoJsonPut;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um insumo cadastrado - Update")
    public void que_existe_um_insumo_cadastrado() {

        String insumoJson = String.format("""
            {
                "nome": "Álcool Gel",
                "custo": 9.90,
                "quantidade": 50,
                "peso": 200,
                "validade": "%s",
                "marca": "Higienix",
                "unidadeMedida": "ML"
            }
            """, dataFormatada);

        Response responseCadastro = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumoJson)
                .when()
                .post("/insumos");

        insumoId = responseCadastro.jsonPath().getString("id");

        insumoJsonPut = String.format("""
            {
                "nome": "Álcool Gel Modificado",
                "custo": 10.50,
                "quantidade": 70,
                "peso": 210,
                "validade": "%s",
                "marca": "Higienix Plus",
                "unidadeMedida": "ML"
            }
            """, dataFormatada);
    }

    @Quando("o insumo for modificado pelo ID - Update")
    public void o_insumo_for_modificado_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumoJsonPut)
                .when()
                .put("/insumos/" + insumoId);
    }

    @Então("o insumo modificado será retornado no sistema - Update")
    public void o_insumo_modificado_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}