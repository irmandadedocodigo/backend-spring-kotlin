package com.irmandadedocodigo.core.controllers.viewmodels.user

import jakarta.validation.constraints.NotBlank

data class CreatePostRequest(
    @field:NotBlank
    var content: String
)
