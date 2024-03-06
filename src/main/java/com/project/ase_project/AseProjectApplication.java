package com.project.ase_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.ase_project.repository.SummonerRepo;
import com.project.ase_project.model.Summoner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class AseProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AseProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner dataLoader(SummonerRepo repository) {
        return args -> {
            repository.save(new Summoner(null, "azerty", "mordeckaiLovuur"));
            repository.save(new Summoner(null, "qwerty", "reneeRaps"));
            repository.save(new Summoner(null, "keysmash", "revolutioN_"));
        };
    }

}
