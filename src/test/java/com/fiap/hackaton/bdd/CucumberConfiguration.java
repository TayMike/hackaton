package com.fiap.hackaton.bdd;

import com.fiap.hackaton.HackatonApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@CucumberContextConfiguration
@SpringBootTest(classes = HackatonApplication.class)
@ActiveProfiles("test")
public class CucumberConfiguration {
}
