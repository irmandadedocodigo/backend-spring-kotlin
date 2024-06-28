package com.irmandadedocodigo.core.services

import com.irmandadedocodigo.core.controllers.viewmodels.posts.MinePostsResponse
import com.irmandadedocodigo.core.controllers.viewmodels.user.CreatePostRequest
import com.irmandadedocodigo.core.infra.entities.Post
import com.irmandadedocodigo.core.infra.repositories.PostRepository
import com.irmandadedocodigo.core.infra.repositories.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostService(
    @Autowired
    private var postRepository: PostRepository,
    @Autowired
    private var userRepository: UserRepository
){
    fun create(post: CreatePostRequest, userId: String) : Post {
        val user = this.userRepository.findById(userId).orElseThrow { EntityNotFoundException() }
        val postEntity = Post(post.content)
        postEntity.author = user
        return postRepository.save(postEntity)
    }

    fun findAll(): List<Post> {
        return this.postRepository.findAll()
    }

    fun findAllById(id: String): List<MinePostsResponse> {
        this.userRepository.findById(id).orElseThrow { EntityNotFoundException() }
        val posts = this.postRepository.findByAuthorId(id)
        return posts.map {
            MinePostsResponse(
                content = it.content,
                id = it.id!!,
                author = MinePostsResponse.Author(
                    id = it.author.id,
                    name = it.author.name
                )
            )
        }
    }
}