package com.project.ase_project.cucumber;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.project.ase_project")
@EnableAutoConfiguration
public class TestConfig {
    // We can leave it empty unless otherwise needed
}