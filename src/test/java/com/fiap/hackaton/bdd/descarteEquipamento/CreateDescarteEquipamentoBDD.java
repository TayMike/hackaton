package com.fiap.hackaton.bdd.descarteEquipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateDescarteEquipamentoBDD {

    private Response response;
    private String descarteEquipamentoJson;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que um descarte de equipamento foi realizado - Criar")
    public void que_um_descarte_de_equipamento_foi_realizado() {
        String hospitalJson = """
        {
            "nome": "Hospital Central",
            "cnpj": "12345678000310",
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

        responseHospital.then().statusCode(201);
        String hospital = responseHospital.jsonPath().getString("id");

        String colaboradorJson = String.format("""
        {
            "cpf": "11122233344",
            "nome": "Maria Souza",
            "matricula": "MAT5678",
            "primeiroDiaCadastro": "%s",
            "cep": "01001000",
            "numeroCasa": 99,
            "hospital": "%s",
            "setor": "TI",
            "ativo": true
        }
        """, dataFormatada, hospital);

        Response responseColaborador = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(colaboradorJson)
                .when()
                .post("/colaboradores");

        responseColaborador.then().statusCode(201);
        String colaborador = responseColaborador.jsonPath().getString("id");

        String equipamentoJson = String.format("""
        {
            "nome": "Monitor Cardíaco",
            "custo": 2500.00,
            "tempoGarantia": "%s",
            "proximaManutencaoPreventiva": "%s",
            "marca": "MedTech",
            "hospital": "%s"
        }
        """, dataFormatada, dataFormatada, hospital);

        Response responseEquipamento = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson)
                .when()
                .post("/equipamentos");

        responseEquipamento.then().statusCode(201);
        String equipamento = responseEquipamento.jsonPath().getString("id");

        descarteEquipamentoJson = String.format("""
        {
            "equipamentos": [
                "%s"
            ],
            "quantidade": [1],
            "colaboradorResponsavel": "%s",
            "dataHoraDescarte": "%s",
            "hospital": "%s"
        }
        """, equipamento, colaborador, dataFormatada, hospital);
    }

    @Quando("o descarte de equipamento for cadastrado - Criar")
    public void o_descarte_de_equipamento_for_cadastrado() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(descarteEquipamentoJson)
                .when()
                .post("/descarteEquipamentos");
    }

    @Então("o descarte de equipamento será salvo no sistema - Criar")
    public void o_descarte_de_equipamento_sera_salvo_no_sistema() {
        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @E("o descarte de equipamento deve estar no formato esperado - Criar")
    public void o_descarte_de_equipamento_deve_estar_no_formato_esperado() {
        response.then().body(matchesJsonSchemaInClasspath("schemas/descarte-equipamento.schema.json"));
    }
}