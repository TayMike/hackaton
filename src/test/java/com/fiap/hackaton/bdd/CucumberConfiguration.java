package com.fiap.hackaton.bdd;

import com.fiap.hackaton.HackatonApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@CucumberContextConfiguration
@SpringBootTest(classes = HackatonApplication.class)
public class CucumberConfiguration {
}
