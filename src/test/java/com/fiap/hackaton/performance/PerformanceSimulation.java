package com.fiap.hackaton.performance;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class PerformanceSimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocolBuilder =
            http.baseUrl("http://localhost:8081")
                    .header("Content-Type", "application/json");

    private final String dataValidade = OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    ScenarioBuilder scenarioBuilder = scenario("Cadastrar insumo")
            // Passo 1: Definir uniqueId para cada usuário
            .exec(session -> {
                // Gerar um ID único baseado no tempo atual e um incremento por usuário
                int uniqueId = Math.toIntExact((System.currentTimeMillis() % 10000) + session.userId());
                return session.set("uniqueId", uniqueId);
            })
            // Passo 2: Criar insumo
            .exec(http("Criar insumo")
                    .post("/insumos")
                    .body(StringBody(session -> String.format("""
                        {
                            "nome": "Dipirona_%s",
                            "custo": 12.50,
                            "quantidade": 100,
                            "peso": 500,
                            "validade": "%s",
                            "marca": "FarmaciaCentral",
                            "unidadeMedida": "MG"
                        }
                        """, session.getInt("uniqueId"), dataValidade)))
                    .check(status().is(201)));

    {
        setUp(scenarioBuilder.injectOpen(
                rampUsersPerSec(1).to(10).during(Duration.ofSeconds(10)),
                constantUsersPerSec(10).during(Duration.ofSeconds(20)),
                rampUsersPerSec(10).to(1).during(Duration.ofSeconds(10))
        ))
                .protocols(httpProtocolBuilder)
                .assertions(global().responseTime().max().lt(1000));
    }
}