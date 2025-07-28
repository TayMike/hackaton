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

public class UpdateEstoqueEquipamentoBDD {

    private Response response;
    private String estoqueEquipamentoId;
    private String estoqueEquipamentoJsonPut;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um estoque de equipamento cadastrado para update - Update")
    public void que_existe_um_estoque_de_equipamento_cadastrado_para_update() {

        String hospitalJson = """
                {
                    "nome": "Hospital Teste",
                    "cnpj": "1234340420100",
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
                  "numeroSerie": "SN124252789",
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

            String colaboradorJson1 = String.format("""
                {
                    "cpf": "82342138901",
                    "nome": "João Silva",
                    "matricula": "LSA2234",
                    "primeiroDiaCadastro": "%s",
                    "ultimoDiaCadastro": null,
                    "cep": "01001000",
                    "numeroCasa": 101,
                    "hospitalId": "%s",
                    "setor": "UTI"
                }
                """, dataFormatada, hospitalId);

            Response colaboradorResponse1 = given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(colaboradorJson1)
                    .when()
                    .post("/colaboradores");

        String colaboradorEntregadorId = colaboradorResponse1.jsonPath().getString("id");

            String colaboradorJson2 = String.format("""
        {
            "cpf": "95341238901",
            "nome": "João Silva",
            "matricula": "SAO1234",
            "primeiroDiaCadastro": "%s",
            "ultimoDiaCadastro": null,
            "cep": "01001000",
            "numeroCasa": 101,
            "hospitalId": "%s",
            "setor": "UTI"
        }
        """, dataFormatada, hospitalId);

            Response colaboradorResponse2 = given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(colaboradorJson2)
                    .when()
                    .post("/colaboradores");

        String colaboradorResponsavelId = colaboradorResponse2.jsonPath().getString("id");

        estoqueEquipamentoJsonPut = String.format("""
                {
                    "colaboradorEntregadorId": "%s",
                    "dataHoraColeta": "%s",
                    "colaboradorResponsavelId": "%s"
                }
                """, colaboradorEntregadorId, dataFormatada, colaboradorResponsavelId);

    }

    @Quando("o estoque de equipamento for modificado pelo ID - Update")
    public void o_estoque_de_equipamento_for_modificado_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(estoqueEquipamentoJsonPut)
                .when()
                .put("/estoque-equipamentos/" + estoqueEquipamentoId);
    }

    @Então("o estoque de equipamento modificado será retornado no sistema - Update")
    public void o_estoque_de_equipamento_modificado_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}