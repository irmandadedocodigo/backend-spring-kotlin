package com.irmandadedocodigo.core.controllers.viewmodels.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class CreateUserRequest(
    @NotBlank
    var name: String,
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    var email: String,
    @NotBlank
    var password: String,
)
