package com.fiap.hackaton.bdd.coletaEquipamento;

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

public class CreateColetaEquipamentoBDD {

    private Response response;
    private String coletaEquipamentoJson;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que uma coleta equipamento foi realizada - Criar")
    public void que_uma_coleta_equipamento_foi_realizada() {
        String hospitalJson = """
            {
                "nome": "Hospital Central",
                "cnpj": "12345678000401",
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
        String hospitalId = responseHospital.jsonPath().getString("id");

        String colaboradorEntregadorJson = String.format("""
            {
                "cpf": "12345678902",
                "nome": "Maria Souza",
                "matricula": "MAT4321",
                "primeiroDiaCadastro": "%s",
                "cep": "02002000",
                "numeroCasa": 202,
                "hospital": "%s",
                "setor": "Cirurgia",
                "ativo": true
            }
            """, dataFormatada, hospitalId);

        Response responseColaboradorEntregador = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(colaboradorEntregadorJson)
                .when()
                .post("/colaboradores");

        responseColaboradorEntregador.then().statusCode(201);
        String colaboradorEntregadorId = responseColaboradorEntregador.jsonPath().getString("id");

        String colaboradorResponsavelJson = String.format("""
            {
                "cpf": "98765432102",
                "nome": "José Pereira",
                "matricula": "MAT5432",
                "primeiroDiaCadastro": "%s",
                "cep": "02003000",
                "numeroCasa": 203,
                "hospital": "%s",
                "setor": "Enfermagem",
                "ativo": true
            }
            """, dataFormatada, hospitalId);

        Response responseColaboradorResponsavel = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(colaboradorResponsavelJson)
                .when()
                .post("/colaboradores");

        responseColaboradorResponsavel.then().statusCode(201);
        String colaboradorResponsavelId = responseColaboradorResponsavel.jsonPath().getString("id");

        String equipamentoJson = String.format("""
            {
                "nome": "Bomba de Infusão",
                "custo": 1500.00,
                "tempoGarantia": "%s",
                "proximaManutencaoPreventiva": "%s",
                "marca": "EquipMed",
                "hospital": "%s"
            }
            """, dataFormatada, dataFormatada, hospitalId);

        Response responseEquipamento = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson)
                .when()
                .post("/equipamentos");

        responseEquipamento.then().statusCode(201);
        String equipamentoId = responseEquipamento.jsonPath().getString("id");

        coletaEquipamentoJson = String.format("""
            {
                "equipamentos": ["%s"],
                "quantidades": [2],
                "colaboradorEntregador": "%s",
                "dataHoraColeta": "%s",
                "colaboradorResponsavel": "%s",
                "hospital": "%s"
            }
            """, equipamentoId, colaboradorEntregadorId, dataFormatada, colaboradorResponsavelId, hospitalId);
    }

    @Quando("a coleta equipamento for cadastrada - Criar")
    public void a_coleta_equipamento_for_cadastrada() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(coletaEquipamentoJson)
                .when()
                .post("/coletaEquipamentos");
    }

    @Então("a coleta equipamento será salva no sistema - Criar")
    public void a_coleta_equipamento_sera_salva_no_sistema() {
        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @E("a coleta equipamento deve estar no formato esperado - Criar")
    public void a_coleta_equipamento_deve_estar_no_formato_esperado() {
        response.then().body(matchesJsonSchemaInClasspath("schemas/coleta-equipamento.schema.json"));
    }
}