package com.irmandadedocodigo.core.controllers

import com.irmandadedocodigo.core.controllers.viewmodels.posts.MinePostsResponse
import com.irmandadedocodigo.core.controllers.viewmodels.user.CreatePostRequest
import com.irmandadedocodigo.core.infra.entities.Post
import com.irmandadedocodigo.core.services.PostService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.security.Principal


@RestController
@RequestMapping("/posts")
@Validated
class PostController(
    @Autowired
    private var postService: PostService,
) {
    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    fun create(@RequestBody @Valid post: CreatePostRequest, principal: Principal): ResponseEntity<Post> {
        val createdPost = this.postService.create(post, principal.name)
        return ResponseEntity.created(URI.create("${createdPost.id}")).body(createdPost)
    }

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    fun listAll(): List<Post> {
        return this.postService.findAll()
    }

    @GetMapping("mine")
    @SecurityRequirement(name = "bearerAuth")
    fun mine(principal: Principal): List<MinePostsResponse> {
        return this.postService.findAllById(principal.name)
    }
}