package com.irmandadedocodigo.core.controllers

import com.irmandadedocodigo.core.infra.entities.User
import com.irmandadedocodigo.core.services.UserService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
@Validated
class UserController(
    @Autowired
    private val userService: UserService,
) {

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    fun getAll(): List<User> {
        return this.userService.all()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: String): User? {
        return this.userService.byId(id)
    }

    @GetMapping("/email/{email}")
    fun getByEmail(@PathVariable("email") email: String): User? {
        return this.userService.byEmail(email)
    }
}
