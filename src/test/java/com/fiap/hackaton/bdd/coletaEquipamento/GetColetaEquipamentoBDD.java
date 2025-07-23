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

public class GetColetaEquipamentoBDD {

    private Response response;
    private String coletaEquipamentoId;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um coleta equipamento cadastrado em um hospital - Get")
    public void que_existe_um_coleta_equipamento_cadastrado_em_um_hospital() {
        String hospitalJson = """
            {
                "nome": "Hospital Central",
                "cnpj": "12345678000402",
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
                "cpf": "12345678903",
                "nome": "Ana Paula",
                "matricula": "MAT5678",
                "primeiroDiaCadastro": "%s",
                "cep": "03003000",
                "numeroCasa": 303,
                "hospital": "%s",
                "setor": "Radiologia",
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
                "cpf": "98765432103",
                "nome": "Carlos Silva",
                "matricula": "MAT6789",
                "primeiroDiaCadastro": "%s",
                "cep": "03004000",
                "numeroCasa": 304,
                "hospital": "%s",
                "setor": "Manutenção",
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
                "nome": "Monitor Cardíaco",
                "custo": 3000.00,
                "tempoGarantia": "%s",
                "proximaManutencaoPreventiva": "%s",
                "marca": "MediTech",
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
                "quantidades": [1],
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

        coletaEquipamentoId = responseColetaEquipamento.jsonPath().getString("id");
    }

    @Quando("um coleta equipamento for buscado pelo ID - Get")
    public void um_coleta_equipamento_for_buscado_pelo_ID() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/coletaEquipamentos/" + coletaEquipamentoId);
    }

    @Então("o coleta equipamento será retornado no sistema - Get")
    public void o_coleta_equipamento_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}