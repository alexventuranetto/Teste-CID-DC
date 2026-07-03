package com.alexsander.descontos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DescontosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DescontosApplication.class, args);
		System.out.println("Aplicação de Descontos rodando em: http://localhost:8080");
		System.out.println("Documentação: https://github.com/alexsander/spring-boot-test-exemplo");
	}
}