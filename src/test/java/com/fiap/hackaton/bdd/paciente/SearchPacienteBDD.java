package com.fiap.hackaton.bdd.paciente;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;

public class SearchPacienteBDD {

    private Response response;

    @Dado("que existem vários pacientes cadastrados - Search")
    public void que_existem_varios_pacientes_cadastrados_search() {
        for (int i = 1; i <= 2; i++) {
            String j = String.valueOf(i);
            String pacienteJson = String.format("""
                {
                    "cpf": "1000000000%s",
                    "nome": "Paciente %s",
                    "primeiroDiaCadastro": "2025-04-0%sT10:00:00Z",
                    "cep": "10000%s",
                    "numeroCasa": %s
                }
                """, j, j, j, j, j);
            given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(pacienteJson)
                    .post("/pacientes")
                    .then()
                    .statusCode(HttpStatus.CREATED.value());
        }
    }

    @Quando("os pacientes forem buscados - Search")
    public void os_pacientes_fore_buscados_search() {
        response = given()
                .when()
                .get("/pacientes");
    }

    @Então("os pacientes serão retornados no sistema - Search")
    public void os_pacientes_serao_retornados_no_sistema_search() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body("$", not(empty()));
    }
}