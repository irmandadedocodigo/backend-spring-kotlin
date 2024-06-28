package com.irmandadedocodigo.core.controllers

import com.irmandadedocodigo.core.controllers.viewmodels.auth.RegisterResponse
import com.irmandadedocodigo.core.infra.entities.User
import com.irmandadedocodigo.core.services.UserService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
@Validated
@SecurityRequirement(name = "bearerAuth")
class UserController(
    @Autowired
    private val userService: UserService,
) {

    @GetMapping
    fun getAll(): List<User> {
        return this.userService.all()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: String): ResponseEntity<RegisterResponse> {
        val user = this.userService.byId(id)
        return ResponseEntity.ok().body(user?.let { RegisterResponse(name = it.name, email = it.email) })
    }

    @GetMapping("/email/{email}")
    fun getByEmail(@PathVariable("email") email: String): ResponseEntity<RegisterResponse> {
        val user =  this.userService.byEmail(email)
        return ResponseEntity.ok().body(user?.let { RegisterResponse(name = it.name, email = it.email) })
    }
}
