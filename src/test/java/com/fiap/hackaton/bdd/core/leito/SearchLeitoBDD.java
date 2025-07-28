package com.fiap.hackaton.bdd.core.leito;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;

public class SearchLeitoBDD {

    private Response response;

    @Dado("que existem vários leitos cadastrados - Search")
    public void que_existem_varios_leitos_cadastrados() {

        for (int i = 1; i <= 2; i++) {
            String hospitalJson = String.format("""
                {
                    "nome": "Hospital Central",
                    "cnpj": "12345678021324%d",
                    "colaboradoresIds": [],
                    "cep": "1234500%d",
                    "numero": 100,
                    "quantidadeLeitoAtual": 10,
                    "quantidadeLeitoMaximo": 20
                }
                """, i, i);

            String pacienteJson = String.format("""
                {
                    "cpf": "1234567890%d",
                    "nome": "Paciente %d",
                    "primeiroDiaCadastro": "2024-01-01T12:00:00Z",
                    "cep": "1234500%d",
                    "numeroCasa": 100
                }
                """, i, i, i);

            String hospitalId = given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(hospitalJson)
                    .post("/hospitais")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .extract()
                    .path("id");

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
                    "identificacao": "L-10%d",
                    "pavilhao": "B",
                    "quarto": "10%d",
                    "hospitalId": "%s",
                    "pacienteId": "%s"
                }
                """, i, i, hospitalId, pacienteId);

            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(leitoJson)
                    .post("/leitos")
                    .then()
                    .statusCode(HttpStatus.CREATED.value());
        }
    }

    @Quando("os leitos forem buscados - Search")
    public void os_leitos_foream_buscados() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/leitos");
    }

    @Então("os leitos serão retornados no sistema - Search")
    public void os_leitos_serao_retornados_no_sistema() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body("$", not(empty()));
    }
}