package com.fiap.hackaton.bdd;

import com.fiap.hackaton.HackatonApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(classes = HackatonApplication.class)
@ActiveProfiles("test")
public class CucumberSpringConfiguration {
}
