package com.pokemon.living_pokedex.domain.model

data class Pokemon (
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