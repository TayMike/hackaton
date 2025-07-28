package com.fiap.hackaton.bdd.equipamentoDominio.estoqueEquipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class SearchEstoqueEquipamentoBDD {

    private Response response;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existem vários estoques de equipamentos cadastrados - Search")
    public void que_existem_varios_estoques_de_equipamentos_cadastrados() {

        String hospitalJson = """
                {
                    "nome": "Hospital Teste",
                    "cnpj": "17279567842010",
                    "colaboradoresIds": [],
                    "cep": "12345678",
                    "numero": 123,
                    "quantidadeLeitoAtual": 10,
                    "quantidadeLeitoMaximo": 50
                }
                """;

        Response hospitalResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(hospitalJson)
                .when()
                .post("/hospitais");

        hospitalResponse.then().statusCode(HttpStatus.CREATED.value());
        String hospitalId = hospitalResponse.jsonPath().getString("id");

        String equipamentoJson1 = String.format("""
                {
                  "nome": "Marca Q",
                  "custo": "1500.00",
                  "tempoGarantia": "%s",
                  "proximaManutencaoPreventiva": "%s",
                  "numeroSerie": "SN122842789",
                  "marca": "Marca D",
                  "descarte": null
                }
                """, dataFormatada, dataFormatada);

        Response equipamentoResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson1)
                .when()
                .post("/equipamentos");

        equipamentoResponse.then().statusCode(HttpStatus.CREATED.value());
        String equipamentoId = equipamentoResponse.jsonPath().getString("id");

        String estoqueEquipamentoJson = String.format("""
                {
                    "equipamentoId": "%s",
                    "hospitalId": "%s",
                    "colaboradorEntregadorId": null,
                    "dataHoraColeta": null,
                    "colaboradorResponsavelId": null
                }
                """, equipamentoId, hospitalId);

        Response estoqueEquipamentoResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(estoqueEquipamentoJson)
                .when()
                .post("/estoque-equipamentos");

        estoqueEquipamentoResponse.then().statusCode(HttpStatus.CREATED.value());

    }

    @Quando("os estoques de equipamentos forem buscados - Search")
    public void os_estoques_de_equipamentos_forem_buscados() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/estoque-equipamentos");
    }

    @Então("os estoques de equipamentos serão retornados no sistema - Search")
    public void os_estoques_de_equipamentos_serao_retornados_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }

}