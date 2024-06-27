package com.irmandadedocodigo.core.services

import com.irmandadedocodigo.core.controllers.viewmodels.auth.RegisterRequest
import com.irmandadedocodigo.core.infra.entities.User
import com.irmandadedocodigo.core.infra.repositories.RoleRepository
import com.irmandadedocodigo.core.infra.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull


@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val encoder: PasswordEncoder,
) : UserDetailsService {

    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String): User? {
        return userRepository.findById(username).getOrNull()
    }

    @Transactional(readOnly = true)
    fun findByName(name: String): User? {
        return this.userRepository.findByName(name)
    }

    @Transactional(readOnly = true)
    fun byEmail(email: String): User? {
        return this.userRepository.findByEmail(email)
    }

    @Transactional(readOnly = true)
    fun byId(id: String): User? {
        return this.userRepository.findById(id).getOrNull()
    }

    @Transactional(readOnly = true)
    fun all(): List<User> {
        return this.userRepository.findAll().toList();
    }

    fun insert(user: RegisterRequest): User {
        val role = roleRepository.findByName("ROLE_USER")
        val newUser = User(user.name, user.email, encoder.encode(user.password))
        newUser.roles = setOf(role!!)
        return userRepository.save(newUser)
    }
}
