package com.fiap.hackaton.bdd.equipamentoDominio.estoqueEquipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class CreateEstoqueEquipamentoBDD {

    private Response response;
    private String estoqueEquipamentoJson;
    private String hospitalId;
    private String equipamentoId;
    private String colaboradorEntregadorId;
    private String colaboradorResponsavelId;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um hospital cadastrado para o estoque de equipamento - Create")
    public void que_existe_um_hospital_cadastrado_para_o_estoque_de_equipamento() {
        String hospitalJson = """
                {
                    "nome": "Hospital Teste",
                    "cnpj": "12345678000100",
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

        hospitalId = hospitalResponse.jsonPath().getString("id");
    }

    @E("que existem equipamentos cadastrados para o estoque - Create")
    public void que_existem_equipamentos_cadastrados_para_o_estoque() {

        String equipamentoJson1 = String.format("""
                {
                  "nome": "Marca K",
                  "custo": "1500.00",
                  "tempoGarantia": "%s",
                  "proximaManutencaoPreventiva": "%s",
                  "numeroSerie": "SN124256789",
                  "marca": "Marca YL",
                  "descarte": null
                }
                """, dataFormatada, dataFormatada);

        Response equipamentoResponse1 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson1)
                .when()
                .post("/equipamentos");

        equipamentoId = equipamentoResponse1.jsonPath().getString("id");
    }

    @E("que um colaborador foi o entregador - Criar")
    public void que_um_colaborador_foi_o_entregador() {
        String colaboradorJson1 = String.format("""
                {
                    "cpf": "12342138901",
                    "nome": "João Silva",
                    "matricula": "MAA2234",
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

        colaboradorEntregadorId = colaboradorResponse1.jsonPath().getString("id");
    }

    @E("que um colaborador foi o responsavel - Criar")
    public void que_um_colaborador_foi_o_responsavel() {
        String colaboradorJson2 = String.format("""
        {
            "cpf": "12341238901",
            "nome": "João Silva",
            "matricula": "MGT1234",
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

        colaboradorResponsavelId = colaboradorResponse2.jsonPath().getString("id");
    }

    @E("que foi construído um estoque de equipamento para criação - Create")
    public void que_foi_construido_um_estoque_de_equipamento_para_criacao() {

        estoqueEquipamentoJson = String.format("""
                {
                    "equipamentoId": "%s",
                    "hospitalId": "%s",
                    "colaboradorEntregadorId": "%s",
                    "dataHoraColeta": "%s",
                    "colaboradorResponsavelId": "%s"
                }
                """, equipamentoId, hospitalId, colaboradorEntregadorId, dataFormatada, colaboradorResponsavelId);
    }

    @Quando("o estoque de equipamento for cadastrado - Create")
    public void o_estoque_de_equipamento_for_cadastrado() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(estoqueEquipamentoJson)
                .when()
                .post("/estoque-equipamentos");
    }

    @Então("o estoque de equipamento será salvo no sistema - Create")
    public void o_estoque_de_equipamento_sera_salvo_no_sistema() {
        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @E("o estoque de equipamento deve estar no formato esperado - Create")
    public void o_estoque_de_equipamento_deve_estar_no_formato_esperado() {
        response.then().body(matchesJsonSchemaInClasspath("schemas/estoque-equipamento.schema.json"));
    }

}