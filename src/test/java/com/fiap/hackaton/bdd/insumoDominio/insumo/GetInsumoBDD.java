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

public class GetInsumoBDD {

    private Response response;
    private String insumoId;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um insumo cadastrado - Get")
    public void que_existe_um_insumo_cadastrado() {
        String insumoJson = String.format("""
                {
                    "nome": "Luvas Cirúrgicas",
                    "custo": 25.90,
                    "quantidade": 200,
                    "peso": 50,
                    "validade": "%s",
                    "marca": "MedProtect",
                    "unidadeMedida": "MG"
                }
                """, dataFormatada);

        Response responseCadastro = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumoJson)
                .when()
                .post("/insumos");

        responseCadastro.then().statusCode(HttpStatus.CREATED.value());
        insumoId = responseCadastro.jsonPath().getString("id");
    }

    @Quando("um insumo for buscado pelo ID - Get")
    public void um_insumo_for_buscado_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/insumos/" + insumoId);
    }

    @Então("o insumo será retornado no sistema - Get")
    public void o_insumo_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}