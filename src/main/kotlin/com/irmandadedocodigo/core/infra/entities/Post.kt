package com.irmandadedocodigo.core.infra.entities

import jakarta.persistence.*

@Entity
@Table(name = "posts")
data class Post(
    @Column(nullable = false)
    var content: String
){

    @OneToOne
    @JoinColumn(name = "user_id")
    lateinit var author: User

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}
