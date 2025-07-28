package com.fiap.hackaton.bdd.core.leito;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class DeleteLeitoBDD {

    private Response response;
    private String leitoId;

    @Dado("que existe um leito cadastrado - Delete")
    public void que_existe_um_leito_cadastrado() {

        String hospitalJson = """
            {
                "nome": "Hospital Central",
                "cnpj": "12345678219502",
                "colaboradoresIds": [],
                "cep": "12345678",
                "numero": 100,
                "quantidadeLeitoAtual": 10,
                "quantidadeLeitoMaximo": 20
            }
            """;
        String hospitalId = given()
                .contentType("application/json")
                .body(hospitalJson)
                .post("/hospitais")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .path("id");

        String pacienteJson = """
            {
                "cpf": "82655678900",
                "nome": "João da Silva",
                "primeiroDiaCadastro": "2024-01-01T12:00:00Z",
                "cep": "12345000",
                "numeroCasa": 123
            }
            """;
        String pacienteId = given()
                .contentType("application/json")
                .body(pacienteJson)
                .post("/pacientes")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .path("id");

        String leitoJson = String.format("""
                {
                    "identificacao": "L-101",
                    "pavilhao": "A",
                    "quarto": "101",
                    "hospitalId": "%s",
                    "pacienteId": "%s"
                }
                """, hospitalId, pacienteId);

        leitoId = given()
                .contentType("application/json")
                .body(leitoJson)
                .post("/leitos")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .path("id");
    }

    @Quando("o leito for deletado pelo ID - Delete")
    public void o_leito_for_deletado_pelo_id() {
        response = given()
                .when()
                .delete("/leitos/" + leitoId);
    }

    @Então("o leito deletado não será retornado no sistema - Delete")
    public void o_leito_deletado_nao_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}