package com.irmandadedocodigo.core.infra.exceptions

import org.springframework.validation.FieldError

data class DataValidationError(val field: String, val message: String?) {
    constructor(error: FieldError) : this(error.field, error.defaultMessage)
}
