package com.fiap.hackaton.bdd.insumo;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class DeleteInsumoBDD {

    private Response response;
    private String insumoId;

    @Dado("que existe um insumo cadastrado - Delete")
    public void que_existe_um_insumo_cadastrado() {
        String insumoJson = """
                {
                    "nome": "Álcool Gel",
                    "custo": 9.90,
                    "quantidade": 50,
                    "peso": 200,
                    "validade": "2026-01-01",
                    "marca": "Higienix",
                    "unidadeMedida": "ML"
                }
                """;

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