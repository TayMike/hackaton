package com.fiap.hackaton.bdd.entregaInsumo;

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

public class CreateEntregaInsumoBDD {

    private String hospitalId;
    private String colaboradorId;
    private String insumoId1;
    private String insumoId2;
    private String entregaJson;
    private Response response;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um hospital cadastrado para entrega de insumo - Create")
    public void que_existe_um_hospital_cadastrado() {
        String hospitalJson = """
            {
                "nome": "Hospital Central",
                "cnpj": "16365678000501",
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

    @E("que existe um colaborador cadastrado para entrega de insumo - Create")
    public void que_existe_um_colaborador_cadastrado() {
        String colaboradorJson = String.format("""
            {
                "cpf": "12312678900",
                "nome": "Maria Souza",
                "matricula": "MAT456",
                "primeiroDiaCadastro": "%s",
                "cep": "12345000",
                "numeroCasa": 123,
                "hospital": "%s",
                "setor": "Recepção",
                "ativo": true
            }
            """, dataFormatada, hospitalId);

        Response responseColaborador = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(colaboradorJson)
                .when()
                .post("/colaboradores");

        responseColaborador.then().statusCode(HttpStatus.CREATED.value());
        colaboradorId = responseColaborador.jsonPath().getString("id");
    }

    @E("que existem insumos cadastrados para entrega - Create")
    public void que_existem_insumos_cadastrados() {
        String insumoJson1 = """
            {
              "nome": "Dipirona",
              "custo": 12.50,
              "quantidade": 100,
              "peso": 500,
              "validade": "2025-12-31",
              "marca": "FarmaciaCentral",
              "unidadeMedida": "MG"
            }
            """;

        String insumoJson2 = """
            {
              "nome": "Paracetamol",
              "custo": 12.50,
              "quantidade": 100,
              "peso": 500,
              "validade": "2025-12-31",
              "marca": "FarmaciaCentral",
              "unidadeMedida": "MG"
            }
            """;

        Response responseEquip1 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumoJson1)
                .when()
                .post("/insumos");
        responseEquip1.then().statusCode(HttpStatus.CREATED.value());
        insumoId1 = responseEquip1.jsonPath().getString("id");

        Response responseEquip2 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumoJson2)
                .when()
                .post("/insumos");
        responseEquip2.then().statusCode(HttpStatus.CREATED.value());
        insumoId2 = responseEquip2.jsonPath().getString("id");
    }

    @E("que foi construída uma entrega de insumos - Create")
    public void que_foi_construida_uma_entrega_de_insumos() {
        entregaJson = String.format("""
            {
                "insumo": ["%s", "%s"],
                "quantidade": [2, 1],
                "colaboradorRecebedor": "%s",
                "dataHoraRecebimento": "%s",
                "hospital": "%s"
            }
            """, insumoId1, insumoId2, colaboradorId, dataFormatada, hospitalId);
    }

    @Quando("a entrega de insumos for cadastrada - Create")
    public void a_entrega_de_insumos_for_cadastrada() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(entregaJson)
                .when()
                .post("/entregaInsumos");
    }

    @Então("a entrega de insumos será salva no sistema - Create")
    public void a_entrega_de_insumos_sera_salva_no_sistema() {
        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @E("a entrega de insumos deve estar no formato esperado - Create")
    public void a_entrega_de_insumos_deve_estar_no_formato_esperado() {
        response.then().body(matchesJsonSchemaInClasspath("schemas/entrega-insumo.schema.json"));
    }

}