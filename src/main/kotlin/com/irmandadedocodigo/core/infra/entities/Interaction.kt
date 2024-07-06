package com.irmandadedocodigo.core.infra.entities

import jakarta.persistence.*


@Entity
@Table(name = "interactions")
data class Interaction(
    @Column(nullable = false)
    var content: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToOne
    @JoinColumn(name = "user_id")
    lateinit var author: User

    @OneToOne
    @JoinColumn(name = "post_id")
    lateinit var post: Post
}
