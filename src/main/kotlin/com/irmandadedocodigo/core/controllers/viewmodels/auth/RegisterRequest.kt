package com.irmandadedocodigo.core.controllers.viewmodels.auth

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class RegisterRequest(
    @field:NotBlank
    val name: String,
    @field:NotBlank
    @field:Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    val email: String,
    @field:NotBlank
    val password: String,
)
