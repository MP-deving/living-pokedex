package com.pokemon.living_pokedex.domain.model

import com.pokemon.living_pokedex.infrastructure.entities.Pokemon as PokemonEntity

data class Pokemon (
    val id: Long? = null,
    val pokedexId: Int,
    val name: String,
    val speciesId: Int,
    val height: Int,
    val weight: Int,
    val baseExperience: Int,
    val order: Int,
    val isDefault: Boolean
) {
    fun toEntity() = PokemonEntity(
        id = id,
        pokedexId = pokedexId,
        name = name,
        speciesId = speciesId,
        height = height,
        weight = weight,
        baseExperience = baseExperience,
        order = order,
        isDefault = isDefault
    )
}