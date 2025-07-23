package com.fiap.hackaton.bdd.leito;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateLeitoBDD {

    private String hospitalId;
    private String pacienteId;
    private String leitoJson;
    private Response response;

    @Dado("que foi criado um hospital cadastrado - Criar")
    public void que_foi_criado_um_hospital_cadastrado() {
        String hospitalJson = """
            {
                "nome": "Hospital Central",
                "cnpj": "12345678000501",
                "colaboradores": [],
                "cep": "12345678",
                "numero": 100,
                "quantidadeLeitoAtual": 10,
                "quantidadeLeitoMaximo": 20
            }
            """;

        Response responseHospital = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(hospitalJson)
                .when()
                .post("/hospitais");

        responseHospital.then().statusCode(HttpStatus.CREATED.value());
        hospitalId = responseHospital.jsonPath().getString("id");
    }

    @E("que foi criado um paciente cadastrado - Criar")
    public void que_foi_criado_um_paciente_cadastrado() {
        String pacienteJson = """
            {
                "cpf": "12345678900",
                "nome": "João da Silva",
                "primeiroDiaCadastro": "2024-01-01T12:00:00Z",
                "cep": "12345000",
                "numeroCasa": 123
            }
            """;

        Response responsePaciente = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(pacienteJson)
                .when()
                .post("/pacientes");

        responsePaciente.then().statusCode(HttpStatus.CREATED.value());
        pacienteId = responsePaciente.jsonPath().getString("id");
    }

    @E("que foi construído um leito com hospital e paciente - Criar")
    public void que_foi_construido_um_leito_com_hospital_e_paciente() {
        leitoJson = String.format("""
            {
                "identificacao": "L-101",
                "pavilhao": "A",
                "quarto": "101",
                "hospital": "%s",
                "paciente": "%s"
            }
            """, hospitalId, pacienteId);
    }

    @Quando("o leito for cadastrado - Criar")
    public void o_leito_for_cadastrado() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(leitoJson)
                .when()
                .post("/leitos");
    }

    @Então("o leito será salvo no sistema - Criar")
    public void o_leito_sera_salvo_no_sistema() {
        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @E("o leito deve estar no formato esperado - Criar")
    public void o_leito_deve_estar_no_formato_esperado() {
        response.then().body(matchesJsonSchemaInClasspath("schemas/leito.schema.json"));
    }
}