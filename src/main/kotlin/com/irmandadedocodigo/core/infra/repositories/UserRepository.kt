package com.irmandadedocodigo.core.infra.repositories

import com.irmandadedocodigo.core.infra.entities.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : CrudRepository<User, String> {

    fun findByEmail(email: String): User?

    fun findByName(name: String): User?

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = :id")
    fun findUserByIdWithRoles(@Param("id") id: String): User?
}