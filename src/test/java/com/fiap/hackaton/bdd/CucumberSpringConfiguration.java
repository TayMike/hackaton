package com.fiap.hackaton.bdd;

import com.fiap.hackaton.HackatonApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = HackatonApplication.class)
public class CucumberSpringConfiguration {
}
