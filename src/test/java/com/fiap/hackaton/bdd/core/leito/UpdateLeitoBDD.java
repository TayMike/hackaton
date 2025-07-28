package com.fiap.hackaton.bdd.core.leito;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class UpdateLeitoBDD {

    private Response response;
    private String leitoId;
    private String leitoJsonPut;

    @Dado("que existe um leito cadastrado - Update")
    public void que_existe_um_leito_cadastrado() {

        String hospitalJson = """
            {
                "nome": "Hospital Central",
                "cnpj": "12345678102505",
                "colaboradoresIds": [],
                "cep": "12345678",
                "numero": 100,
                "quantidadeLeitoAtual": 10,
                "quantidadeLeitoMaximo": 20
            }
            """;
        String hospitalId = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(hospitalJson)
                .post("/hospitais")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .path("id");

        String pacienteJson = """
            {
                "cpf": "14315678900",
                "nome": "João da Silva",
                "primeiroDiaCadastro": "2024-01-01T12:00:00Z",
                "cep": "12345000",
                "numeroCasa": 123
            }
            """;
        String pacienteId = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(pacienteJson)
                .post("/pacientes")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .path("id");

        String leitoJson = String.format("""
            {
                "identificacao": "B-101",
                "pavilhao": "A",
                "quarto": "101",
                "hospitalId": "%s",
                "pacienteId": "%s"
            }
            """, hospitalId, pacienteId);

        leitoId = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(leitoJson)
                .post("/leitos")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .path("id");

        leitoJsonPut = String.format("""
            {
                "identificacao": "B-202",
                "pavilhao": "B",
                "quarto": "202",
                "hospitalId": "%s",
                "pacienteId": "%s"
            }
            """, hospitalId, pacienteId);
    }

    @Quando("o leito for modificado pelo ID - Update")
    public void o_leito_for_modificado_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(leitoJsonPut)
                .when()
                .put("/leitos/" + leitoId);
    }

    @Então("o leito modificado será retornado no sistema - Update")
    public void o_leito_modificado_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}