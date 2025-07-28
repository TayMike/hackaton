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

public class GetEstoqueEquipamentoBDD {

    private Response response;
    private String estoqueEquipamentoId;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um estoque de equipamento cadastrado - Get")
    public void que_existe_um_estoque_de_equipamento_cadastrado() {

        String hospitalJson = """
                {
                    "nome": "Hospital Teste",
                    "cnpj": "12345530420100",
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
                  "nome": "Marca A",
                  "custo": "1500.00",
                  "tempoGarantia": "%s",
                  "proximaManutencaoPreventiva": "%s",
                  "numeroSerie": "SN756252789",
                  "marca": "Marca R",
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
        estoqueEquipamentoId = estoqueEquipamentoResponse.jsonPath().getString("id");

    }

    @Quando("um estoque de equipamento for buscado pelo ID - Get")
    public void um_estoque_de_equipamento_for_buscado_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/estoque-equipamentos/" + estoqueEquipamentoId);
    }

    @Então("o estoque de equipamento será retornado no sistema - Get")
    public void o_estoque_de_equipamento_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }

}