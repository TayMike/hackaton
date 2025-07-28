package com.fiap.hackaton.bdd.core.leito;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class GetLeitoBDD {

    private Response response;
    private String leitoId;

    @Dado("que existe um leito cadastrado - Get")
    public void que_existe_um_leito_cadastrado() {

        String hospitalJson = """
            {
                "nome": "Hospital Central",
                "cnpj": "12345678542503",
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
                "cpf": "12345678900",
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
                    "identificacao": "L-101",
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
    }

    @Quando("um leito for buscado pelo ID - Get")
    public void um_leito_for_buscado_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/leitos/" + leitoId);
    }

    @Então("o leito será retornado no sistema - Get")
    public void o_leito_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}