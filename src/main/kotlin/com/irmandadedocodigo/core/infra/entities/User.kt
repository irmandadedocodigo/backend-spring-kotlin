package com.irmandadedocodigo.core.infra.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users", indexes = [Index(name = "idx_email", columnList = "email")])
data class User(
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(name = "password", nullable = false)
    var pass: String,
) : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: String

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    @JsonBackReference
    var roles: Set<Role> = HashSet()

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return this.roles.map { role -> SimpleGrantedAuthority(role.name) }
    }

    override fun getPassword(): String {
        return this.pass
    }

    override fun getUsername(): String {
        return this.id
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}