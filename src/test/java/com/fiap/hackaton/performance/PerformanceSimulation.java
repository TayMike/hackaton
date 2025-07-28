package com.fiap.hackaton.performance;

import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class PerformanceSimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocolBuilder =
            http.baseUrl("http://localhost:8080")
                    .header("Content-Type", "application/json");

    private static String hospitalId;
    private static String colaboradorId;
    private static String insumoId1;
    private static String insumoId2;
    private static final String dataHoraRecebimento;

    static {
        try {
            Class.forName("org.h2.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'src/test/resources/init-h2.sql'")) {
                // Consultar hospital
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT id FROM hospital LIMIT 1")) {
                    if (rs.next()) hospitalId = rs.getString("id");
                }

                // Consultar colaborador
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT id FROM colaborador LIMIT 1")) {
                    if (rs.next()) colaboradorId = rs.getString("id");
                    System.out.println("colaboradorId retrieved from H2: " + colaboradorId);
                }

                // Consultar insumos
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT id FROM insumo ORDER BY id")) {
                    if (rs.next()) insumoId1 = rs.getString("id");
                    if (rs.next()) insumoId2 = rs.getString("id");
                }

                // Definir dataHoraRecebimento
                dataHoraRecebimento = OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar o banco H2 ou consultar UUIDs", e);
        }
    }

    ActionBuilder cadastrarEntregaInsumosRequest = http("Cadastrar entrega insumos")
            .post("/entrega-insumos")
            .body(StringBody(
                    String.format("""
                    {
                        "insumos": [
                            {"insumoId": "%s", "quantidade": 250},
                            {"insumoId": "%s", "quantidade": 500}
                        ],
                        "colaboradorRecebedorId": "%s",
                        "dataHoraRecebimento": "%s",
                        "hospitalId": "%s"
                    }
                    """, insumoId1, insumoId2, colaboradorId, dataHoraRecebimento, hospitalId)
            ))
            .check(status().is(201));

    ScenarioBuilder scenarioBuilder = scenario("Cadastrar entrega insumos")
            .exec(cadastrarEntregaInsumosRequest);

    {
        setUp(scenarioBuilder.injectOpen(
                rampUsersPerSec(1).to(10).during(Duration.ofSeconds(10)),
                constantUsersPerSec(10).during(Duration.ofSeconds(20)),
                rampUsersPerSec(10).to(1).during(Duration.ofSeconds(10))
        ))
                .protocols(httpProtocolBuilder)
                .assertions(global().responseTime().max().lt(50));
    }
}