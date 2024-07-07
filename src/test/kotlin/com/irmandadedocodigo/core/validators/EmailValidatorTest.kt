package com.irmandadedocodigo.core.validators

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

class EmailValidatorTest {

    @Test
    @DisplayName("Should return that the email is invali.")
    fun should_return_invalid() {
        val email = "teste.com"
        assertFalse(EmailValidator().validate(email))
    }

    @Test
    @DisplayName("Should return that the email is valid")
    fun should_return_valid() {
        val email = "teste@teste.com"
        assertTrue(EmailValidator().validate(email))
    }
}