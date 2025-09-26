package com.pokemon.living_pokedex.infrastructure.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collation = "pokemon")
data class Pokemon(
    @Id
    val id: String?,
    val pokedexId: Int,
    val name: String,
    val speciesId: Int,
    val height: Int,
    val weight: Int,
    val baseExperience: Int,
    val order: Int,
    val isDefault: Boolean
)