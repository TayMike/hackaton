package com.fiap.hackaton.bdd.equipamentoDominio.entregaEquipamento;

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

public class CreateEntregaEquipamentoBDD {

    private String hospitalId;
    private String colaboradorId;
    private String equipamentoId;
    private String entregaJson;
    private Response response;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um hospital cadastrado para entrega de equipamento - Create")
    public void que_existe_um_hospital_cadastrado() {
        String hospitalJson = """
            {
                "nome": "Hospital Central",
                "cnpj": "12345678000501",
                "colaboradoresIds": [],
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

    @E("que existe um colaborador cadastrado para entrega de equipamento - Create")
    public void que_existe_um_colaborador_cadastrado() {
        String colaboradorJson = String.format("""
            {
                "cpf": "12213067890",
                "nome": "João Silva",
                "matricula": "MAS1234",
                "primeiroDiaCadastro": "%s",
                "ultimoDiaCadastro": null,
                "cep": "01001000",
                "numeroCasa": 101,
                "hospitalId": "%s",
                "setor": "UTI"
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

    @E("que existem equipamentos cadastrados para entrega - Create")
    public void que_existem_equipamentos_cadastrados() {
        String equipamentoJson = String.format("""
        {
          "nome": "Bomba de Infusão",
          "custo": "1500.00",
          "tempoGarantia": "%s",
          "proximaManutencaoPreventiva": "%s",
          "numeroSerie": "SN123456789",
          "marca": "MedTech",
          "descarte": null
        }
        """, dataFormatada, dataFormatada);

        Response responseEquip = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson)
                .when()
                .post("/equipamentos");
        responseEquip.then().statusCode(HttpStatus.CREATED.value());
        equipamentoId = responseEquip.jsonPath().getString("id");
    }

    @E("que foi construída uma entrega de equipamentos - Create")
    public void que_foi_construida_uma_entrega_de_equipamentos() {
        entregaJson = String.format("""
            {
                "equipamentoId": "%s",
                "colaboradorRecebedorId": "%s",
                "dataHoraRecebimento": "%s",
                "hospitalId": "%s"
            }
            """, equipamentoId, colaboradorId, dataFormatada, hospitalId);
    }

    @Quando("a entrega de equipamentos for cadastrada - Create")
    public void a_entrega_de_equipamentos_for_cadastrada() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(entregaJson)
                .when()
                .post("/entrega-equipamentos");
    }

    @Então("a entrega de equipamentos será salva no sistema - Create")
    public void a_entrega_de_equipamentos_sera_salva_no_sistema() {
        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @E("a entrega de equipamentos deve estar no formato esperado - Create")
    public void a_entrega_de_equipamentos_deve_estar_no_formato_esperado() {
        response.then().body(matchesJsonSchemaInClasspath("schemas/entrega-equipamento.schema.json"));
    }

}