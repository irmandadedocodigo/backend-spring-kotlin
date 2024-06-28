package com.irmandadedocodigo.core.controllers.viewmodels.auth

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class AuthRequest(
    @field:NotBlank
    @field:Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    var email: String?,
    @field:NotBlank
    var password: String?,
)
