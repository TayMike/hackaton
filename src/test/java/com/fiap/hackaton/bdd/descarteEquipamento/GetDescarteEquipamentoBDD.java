package com.fiap.hackaton.bdd.descarteEquipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class GetDescarteEquipamentoBDD {

    private Response response;
    private String descarteId;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um descarte de equipamento cadastrado em um hospital - Get")
    public void que_existe_um_descarte_de_equipamento_cadastrado_em_um_hospital() {
        String hospitalJson = """
        {
            "nome": "Hospital Norte",
            "cnpj": "22345678000199",
            "colaboradores": [],
            "cep": "11111111",
            "numero": 200,
            "quantidadeLeitoAtual": 5,
            "quantidadeLeitoMaximo": 15
        }
        """;

        Response responseHospital = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(hospitalJson)
                .when()
                .post("/hospitais");

        responseHospital.then().statusCode(201);
        String hospital = responseHospital.jsonPath().getString("id");

        String colaboradorJson = String.format("""
        {
            "cpf": "55566677788",
            "nome": "Carlos Lima",
            "matricula": "MAT9999",
            "primeiroDiaCadastro": "%s",
            "cep": "22222222",
            "numeroCasa": 77,
            "hospital": "%s",
            "setor": "TI",
            "ativo": true
        }
        """, dataFormatada, hospital);

        Response responseColaborador = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(colaboradorJson)
                .when()
                .post("/colaboradores");

        responseColaborador.then().statusCode(201);
        String colaborador = responseColaborador.jsonPath().getString("id");

        String equipamentoJson = String.format("""
        {
            "nome": "Desfibrilador",
            "custo": 5000.00,
            "tempoGarantia": "%s",
            "proximaManutencaoPreventiva": "%s",
            "marca": "CardioTech",
            "hospital": "%s"
        }
        """, dataFormatada, dataFormatada, hospital);

        Response responseEquipamento = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson)
                .when()
                .post("/equipamentos");

        responseEquipamento.then().statusCode(201);
        String equipamento = responseEquipamento.jsonPath().getString("id");

        String descarteJson = String.format("""
        {
            "equipamentos": ["%s"],
            "quantidade": [1],
            "colaboradorResponsavel": "%s",
            "dataHoraDescarte": "%s",
            "hospital": "%s"
        }
        """, equipamento, colaborador, dataFormatada, hospital);

        Response responseDescarte = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(descarteJson)
                .when()
                .post("/descarteEquipamentos");

        responseDescarte.then().statusCode(201);
        descarteId = responseDescarte.jsonPath().getString("id");
    }

    @Quando("um descarte de equipamento for buscado pelo ID - Get")
    public void um_descarte_de_equipamento_for_buscado_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/descarteEquipamentos/" + descarteId);
    }

    @Então("o descarte de equipamento será retornado no sistema - Get")
    public void o_descarte_de_equipamento_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}