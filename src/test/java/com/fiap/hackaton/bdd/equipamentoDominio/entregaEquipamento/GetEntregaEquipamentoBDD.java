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

public class GetEntregaEquipamentoBDD {

    private String hospitalId;
    private String colaboradorId;
    private String equipamentoId;
    private Response response;
    private String entregaId;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um hospital cadastrado para entrega de equipamento - Get")
    public void que_existe_um_hospital_cadastrado() {
        String hospitalJson = """
            {
                "nome": "Hospital Central",
                "cnpj": "23456780020",
                "colaboradoresIds": [],
                "cep": "12345678",
                "numero": 100,
                "quantidadeLeitoAtual": 15,
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

    @E("que existe um colaborador cadastrado para entrega de equipamento - Get")
    public void que_existe_um_colaborador_cadastrado() {
        String colaboradorJson = String.format("""
            {
                "cpf": "223063290",
                "nome": "João Silva",
                "matricula": "MAR234",
                "primeiroDiaCadastro": "%s",
                "ultimoDiaCadastro": null,
                "cep": "000000",
                "numeroCasa": 0,
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

    @E("que existem equipamentos cadastrados para entrega - Get")
    public void que_existem_equipamentos_cadastrados() {
        String equipamentoJson = String.format("""
            {
                "nome": "Bomba de Gás",
                "custo": 300.00,
                "tempoGarantia": "%s",
                "proximaManutencaoPreventiva": "%s",
                "numeroSerie": "ST123456789",
                "marca": "MedTech",
                "hospitalId": "%s"
            }
            """, dataFormatada, dataFormatada, hospitalId);

        Response responseEquip = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson)
                .when()
                .post("/equipamentos");
        responseEquip.then().statusCode(HttpStatus.CREATED.value());
        equipamentoId = responseEquip.jsonPath().getString("id");
    }

    @E("que foi construída uma entrega de equipamentos - Get")
    public void que_foi_construida_uma_entrega_de_equipamentos() {
        String entregaJson = String.format("""
                {
                "equipamentoId": "%s",
                "colaboradorRecebedorId": "%s",
                "dataHoraRecebimento": "%s",
                "hospitalId": "%s"
                }
                """, equipamentoId, colaboradorId, dataFormatada, hospitalId);

        Response responseEntrega = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(entregaJson)
                .when()
                .post("/entrega-equipamentos");
        responseEntrega.then().statusCode(HttpStatus.CREATED.value());
        entregaId = responseEntrega.jsonPath().getString("id");
    }

    @Quando("buscar entregas de equipamentos por hospital - Get")
    public void buscar_entregas_de_equipamentos_por_hospital() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/entrega-equipamentos/" + entregaId);
    }

    @Então("deve retornar as entregas de equipamentos do hospital - Get")
    public void deve_retornar_as_entregas_de_equipamentos_do_hospital() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}