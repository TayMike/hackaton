package com.fiap.hackaton.bdd.core.paciente;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreatePacienteBDD {

    private String pacienteJson;
    private Response response;

    @Dado("que foi construído um paciente - Criar")
    public void que_foi_construído_um_paciente_criar() {
        pacienteJson = """
                {
                    "cpf": "12345678900",
                    "nome": "João da Silva",
                    "primeiroDiaCadastro": "2025-01-01T10:00:00Z",
                    "cep": "12345000",
                    "numeroCasa": 100
                }
                """;
    }

    @Quando("o paciente for cadastrado - Criar")
    public void o_paciente_for_cadastrado_criar() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(pacienteJson)
                .when()
                .post("/pacientes");
    }

    @Então("o paciente será salvo no sistema - Criar")
    public void o_paciente_será_salvo_no_sistema_criar() {
        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @E("o paciente deve estar no formato esperado - Criar")
    public void o_paciente_deve_estar_no_formato_esperado_criar() {
        response.then().body(matchesJsonSchemaInClasspath("schemas/paciente.schema.json"));
    }
}