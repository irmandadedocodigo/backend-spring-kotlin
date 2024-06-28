package com.irmandadedocodigo.core.controllers

import com.irmandadedocodigo.core.controllers.viewmodels.auth.AuthRequest
import com.irmandadedocodigo.core.controllers.viewmodels.auth.AuthResponse
import com.irmandadedocodigo.core.controllers.viewmodels.auth.RegisterRequest
import com.irmandadedocodigo.core.infra.entities.User
import com.irmandadedocodigo.core.services.TokenService
import com.irmandadedocodigo.core.services.UserService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/auth")
class AuthController internal constructor(
    private val tokenService: TokenService,
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager,
) {
    @PostMapping("register")
    fun addNewUser(@RequestBody @Valid request: RegisterRequest): ResponseEntity<User> {
        return ResponseEntity.ok(this.userService.insert(request))
    }

    @PostMapping("login")
    fun login(@RequestBody() @Valid authRequest: AuthRequest): ResponseEntity<AuthResponse> {
        val user = this.userService.byEmail(authRequest.email!!)
        val usernamePassword = UsernamePasswordAuthenticationToken(user?.id, authRequest.password)
        val auth = authenticationManager.authenticate(usernamePassword)

        val token = tokenService.generate(auth.principal as User)

        return ResponseEntity.ok(AuthResponse(token!!))
    }


    @GetMapping("/profile")
    @SecurityRequirement(name = "bearerAuth")
    fun profile(principal: Principal): String {
        return principal.name
    }

    @GetMapping("/admin-profile")
    @SecurityRequirement(name = "bearerAuth")
    fun adminProfile(principal: Principal): Principal {
        return principal
    }

}