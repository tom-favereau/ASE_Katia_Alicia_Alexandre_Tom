package com.project.ase_project.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@CucumberContextConfiguration
@SpringBootTest(classes = TestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberSpringConfiguration {

    @LocalServerPort
    private int port;

    @PostConstruct
    public void setup() {
        System.setProperty("port", String.valueOf(port));
    }
}