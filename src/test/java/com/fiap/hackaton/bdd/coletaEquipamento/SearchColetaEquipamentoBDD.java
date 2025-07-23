package com.fiap.hackaton.bdd.coletaEquipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class SearchColetaEquipamentoBDD {

    private Response response;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existem vários coletaEquipamentos cadastrados em um hospital - Search")
    public void que_existem_varios_coletaEquipamentos_cadastrados_em_um_hospital() {
        String hospitalJson = """
            {
                "nome": "Hospital Central",
                "cnpj": "12345678000403",
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
                "cpf": "12345678904",
                "nome": "Lucas Oliveira",
                "matricula": "MAT7890",
                "primeiroDiaCadastro": "%s",
                "cep": "04004000",
                "numeroCasa": 404,
                "hospital": "%s",
                "setor": "TI",
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
                "cpf": "98765432104",
                "nome": "Fernanda Lima",
                "matricula": "MAT8901",
                "primeiroDiaCadastro": "%s",
                "cep": "04005000",
                "numeroCasa": 405,
                "hospital": "%s",
                "setor": "Compras",
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
                "nome": "Ventilador Pulmonar",
                "custo": 4500.00,
                "tempoGarantia": "%s",
                "proximaManutencaoPreventiva": "%s",
                "marca": "RespiraTech",
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

        String coletaEquipamentoJson = String.format("""
            {
                "equipamentos": ["%s"],
                "quantidades": [3],
                "colaboradorEntregador": "%s",
                "dataHoraColeta": "%s",
                "colaboradorResponsavel": "%s",
                "hospital": "%s"
            }
            """, equipamentoId, colaboradorEntregadorId, dataFormatada, colaboradorResponsavelId, hospitalId);

        Response responseColetaEquipamento = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(coletaEquipamentoJson)
                .when()
                .post("/coletaEquipamentos");
        responseColetaEquipamento.then().statusCode(201);
    }

    @Quando("os coletaEquipamentos foram buscados pelo ID - Search")
    public void os_coletaEquipamentos_foram_buscados_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/coletaEquipamentos");
    }

    @Então("os coletaEquipamentos serão retornados no sistema - Search")
    public void os_coletaEquipamentos_serao_retornados_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}