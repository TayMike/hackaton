package com.fiap.hackaton.bdd.insumoDominio.coletaInsumo;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class GetColetaInsumoBDD {

    private Response response;
    private String coletaInsumoId;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um coleta insumo cadastrado em um hospital - Get")
    public void que_existe_um_coleta_insumo_cadastrado_em_um_hospital() {
        String hospitalJson = """
                {
                    "nome": "Hospital Central",
                    "cnpj": "16365123183501",
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
        String hospitalId = responseHospital.jsonPath().getString("id");

        String colaboradorJson = String.format("""
                {
                    "cpf": "26263678918",
                    "nome": "Maria Souza",
                    "matricula": "NBF456",
                    "primeiroDiaCadastro": "%s",
                    "ultimoDiaCadastro": null,
                    "cep": "12345000",
                    "numeroCasa": 123,
                    "hospitalId": "%s",
                    "setor": "Recepção"
                }
                """, dataFormatada, hospitalId);

        Response responseColaborador = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(colaboradorJson)
                .when()
                .post("/colaboradores");

        responseColaborador.then().statusCode(HttpStatus.CREATED.value());
        String colaboradorId = responseColaborador.jsonPath().getString("id");

        String pacienteJson = """
                {
                    "cpf": "12345693925",
                    "nome": "João da Silva",
                    "primeiroDiaCadastro": "2025-01-01T10:00:00Z",
                    "cep": "12345000",
                    "numeroCasa": 100
                }
                """;

        Response responsePaciente = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(pacienteJson)
                .when()
                .post("/pacientes");

        responsePaciente.then().statusCode(HttpStatus.CREATED.value());
        String pacienteId = responsePaciente.jsonPath().getString("id");

        String insumoJson1 = String.format("""
                {
                  "nome": "Dipiro",
                  "custo": 12.50,
                  "quantidade": 100,
                  "peso": 500,
                  "validade": "%s",
                  "marca": "FarmaciaCentral",
                  "unidadeMedida": "MG"
                }
                """, dataFormatada);

        String insumoJson2 = String.format("""
                {
                  "nome": "Paraceta",
                  "custo": 12.50,
                  "quantidade": 100,
                  "peso": 500,
                  "validade": "%s",
                  "marca": "FarmaciaCentral",
                  "unidadeMedida": "MG"
                }
                """, dataFormatada);

        Response responseInsumo1 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumoJson1)
                .when()
                .post("/insumos");
        responseInsumo1.then().statusCode(HttpStatus.CREATED.value());
        String insumoId1 = responseInsumo1.jsonPath().getString("id");

        Response responseInsumo2 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumoJson2)
                .when()
                .post("/insumos");
        responseInsumo2.then().statusCode(HttpStatus.CREATED.value());
        String insumoId2 = responseInsumo2.jsonPath().getString("id");

        String entregaInsumoJson = String.format("""
                {
                    "insumos": [
                        {"insumoId": "%s", "quantidade": 210},
                        {"insumoId": "%s", "quantidade": 490}
                    ],
                    "colaboradorRecebedorId": "%s",
                    "dataHoraRecebimento": "%s",
                    "hospitalId": "%s"
                }
                """, insumoId1, insumoId2, colaboradorId, dataFormatada, hospitalId);

        Response responseEntrega = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(entregaInsumoJson)
                .when()
                .post("/entrega-insumos");
        responseEntrega.then().statusCode(HttpStatus.CREATED.value());

        String coletaInsumoJson = String.format("""
                {
                    "insumos": [
                        {"insumoId": "%s", "quantidade": 210},
                        {"insumoId": "%s", "quantidade": 490}
                    ],
                    "colaboradorEntregadorId": "%s",
                    "dataHoraColeta": "%s",
                    "pacienteRecebedorId": "%s",
                    "hospitalId": "%s"
                }
                """, insumoId1, insumoId2, colaboradorId, dataFormatada, pacienteId, hospitalId);

        Response responseColeta = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(coletaInsumoJson)
                .when()
                .post("/coleta-insumos");
        responseColeta.then().statusCode(HttpStatus.CREATED.value());
        coletaInsumoId = responseColeta.jsonPath().getString("id");
    }

    @Quando("um coleta insumo for buscado pelo ID - Get")
    public void um_coleta_insumo_for_buscado_pelo_ID() {

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/coleta-insumos/" + coletaInsumoId);
    }

    @Então("o coleta insumo será retornado no sistema - Get")
    public void o_coleta_insumo_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }

}
