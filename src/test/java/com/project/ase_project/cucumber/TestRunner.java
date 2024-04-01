package com.project.ase_project.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/com/project/ase_project/cucumber",
        glue = "com.project.ase_project.cucumber",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TestRunner {
    // Can be left empty unless otherwise needed
}