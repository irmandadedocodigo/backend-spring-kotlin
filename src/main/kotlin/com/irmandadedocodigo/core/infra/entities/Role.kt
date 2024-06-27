package com.irmandadedocodigo.core.infra.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "roles", indexes = [Index(name = "idx_role_name", columnList = "name")])
data class Role(
    @Column(nullable = false, unique = true)
    var name: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    var users: Set<User> = HashSet()
}