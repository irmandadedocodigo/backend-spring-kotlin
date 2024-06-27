package com.irmandadedocodigo.core.config

import com.irmandadedocodigo.core.infra.repositories.UserRepository
import com.irmandadedocodigo.core.services.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityFilter : OncePerRequestFilter() {
    @Autowired
    private lateinit var tokenService: TokenService

    @Autowired
    private lateinit var userEntityRepository: UserRepository

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {

        val authHeader = request.getHeader("Authorization") ?: null
        val token = authHeader?.replace("Bearer ", "")
        token?.let {
            val subject = tokenService.validate(it)
            val user = this.userEntityRepository.findUserByIdWithRoles(subject!!)
            val authentication = UsernamePasswordAuthenticationToken(user?.username, null, user?.authorities)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

}