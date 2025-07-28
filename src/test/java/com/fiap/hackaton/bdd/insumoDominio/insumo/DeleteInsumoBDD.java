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

public class DeleteInsumoBDD {

    private Response response;
    private String insumoId;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um insumo cadastrado - Delete")
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

        responseCadastro.then().statusCode(HttpStatus.CREATED.value());

        insumoId = responseCadastro.jsonPath().getString("id");
    }

    @Quando("o insumo for deletado pelo ID - Delete")
    public void o_insumo_for_deletado_pelo_id() {
        response = given()
                .when()
                .delete("/insumos/" + insumoId);
    }

    @Então("o insumo deletado não será retornado no sistema - Delete")
    public void o_insumo_deletado_nao_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}