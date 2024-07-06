package com.irmandadedocodigo.core.infra.repositories

import com.irmandadedocodigo.core.infra.entities.Interaction
import org.springframework.data.jpa.repository.JpaRepository

interface InteractionRepository : JpaRepository<Interaction, Long> {}
