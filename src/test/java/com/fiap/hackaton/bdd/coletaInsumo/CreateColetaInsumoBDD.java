package com.fiap.hackaton.bdd.coletaInsumo;

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

public class CreateColetaInsumoBDD {

    private Response response;
    private String coletaInsumoJson;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que uma coleta insumo foi realizada - Criar")
    public void que_um_coleta_insumo_foi_realizada() {
        String hospitalJson = """
    {
        "nome": "Hospital Central",
        "cnpj": "12345678000301",
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
            "cpf": "12345678901",
            "nome": "João Silva",
            "matricula": "MAT1234",
            "primeiroDiaCadastro": "%s",
            "cep": "01001000",
            "numeroCasa": 101,
            "hospital": "%s",
            "setor": "UTI",
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

        String pacienteJson = String.format("""
        {
          "cpf": "12345678900",
          "nome": "João da Silva",
          "primeiroDiaCadastro": "%s",
          "cep": "12345678",
          "numeroCasa": 123
        }
        """, dataFormatada);

        Response responsePaciente = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(pacienteJson)
                .when()
                .post("/pacientes");

        responsePaciente.then().statusCode(201);

        String paciente = responsePaciente.jsonPath().getString("id");

        String insumoJson = """
        {
          "nome": "Dipirona",
          "custo": 12.50,
          "quantidade": 100,
          "peso": 500,
          "validade": "2025-12-31",
          "marca": "Genérico",
          "unidadeMedida": "MG"
        }
        """;

        Response responseInsumo = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumoJson)
                .when()
                .post("/insumos");

        responseInsumo.then().statusCode(201);

        String insumo = responseInsumo.jsonPath().getString("id");

        coletaInsumoJson = String.format("""
            {
             "insumos": [
                "%s"
              ],
              "quantidades": [5],
              "colaboradorEntregador": "%s",
              "dataHoraColeta": "%s",
              "pacienteRecebedor": "%s",
              "hospital": "%s"
            }
            """, insumo, colaborador, dataFormatada, paciente, hospital);
    }

    @Quando("a coleta insumo for cadastrada - Criar")
    public void a_coleta_insumo_for_cadastrada() {

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(coletaInsumoJson)
                .when()
                .post("/coletaInsumos");
    }

    @Então("a coleta insumo será salva no sistema - Criar")
    public void o_coleta_insumo_sera_salvo_no_sistema() {
        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @E("a coleta insumo deve estar no formato esperado - Criar")
    public void o_coleta_insumo_deve_estar_no_formato_esperado() {
        response.then().body(matchesJsonSchemaInClasspath("schemas/coleta-insumo.schema.json"));
    }

}
