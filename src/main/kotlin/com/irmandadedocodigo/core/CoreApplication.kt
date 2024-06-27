package com.irmandadedocodigo.core

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(info = Info(title = "Irmandade do Código - API", version = "v1"))

class AuthApplication

fun main(args: Array<String>) {
	runApplication<AuthApplication>(*args)
}
