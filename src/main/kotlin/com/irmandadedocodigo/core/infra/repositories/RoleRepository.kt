package com.irmandadedocodigo.core.infra.repositories

import com.irmandadedocodigo.core.infra.entities.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: String): Role?
}