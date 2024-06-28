package com.irmandadedocodigo.core.infra.repositories

import com.irmandadedocodigo.core.infra.entities.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface PostRepository: JpaRepository<Post, Long> {
    fun findByAuthorId(@Param("id")id: String): List<Post>
}