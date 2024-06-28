package com.irmandadedocodigo.core.infra.exceptions

import jakarta.persistence.EntityNotFoundException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class  ExceptionHandlerAdvice {
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleNotFound (): ResponseEntity<*> {
        return ResponseEntity.notFound().build<Any>()
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleBadRequest(ex: MethodArgumentNotValidException): ResponseEntity<*> {
        val errors = ex.fieldErrors
        return ResponseEntity.badRequest().body(errors.map { error: FieldError -> DataValidationError(error) }.toList())
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleBadRequest(ex: DataIntegrityViolationException): ResponseEntity<*> {
        return ResponseEntity.badRequest().body(ex.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleInternalServerError(ex: Exception): ResponseEntity<*> {
        return ResponseEntity.internalServerError().body(ex.message)
    }

}