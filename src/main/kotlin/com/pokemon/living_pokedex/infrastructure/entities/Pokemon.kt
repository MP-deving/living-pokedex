package com.pokemon.living_pokedex.infrastructure.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "pokemon")
@Entity
data class Pokemon(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(name = "pokedex_id", nullable = false, unique = true)
    val pokedexId: Int,
    @Column(name = "name", nullable = false)
    val name: String,
    @Column(name = "species_id")
    val speciesId: Int?,
    @Column(name = "height", nullable = false)
    val height: Int,
    @Column(name = "weight", nullable = false)
    val weight: Int,
    @Column(name = "base_experience", nullable = false)
    val baseExperience: Int,
    @Column(name = "order_number", nullable = false)
    val order: Int,
    @Column(name = "is_default", nullable = false)
    val isDefault: Boolean
)