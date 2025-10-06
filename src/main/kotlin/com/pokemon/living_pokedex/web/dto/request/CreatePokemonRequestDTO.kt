package com.pokemon.living_pokedex.web.dto.request

import com.pokemon.living_pokedex.domain.model.Pokemon
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class CreatePokemonRequestDTO (
    @field:NotBlank
    val name: String,
    val speciesId: Int? = 0,
    @field:NotNull
    @field:Positive
    val height: Int,
    @field:NotNull
    @field:Positive
    val weight: Int,
    @field:NotNull
    @field:Positive
    val baseExperience: Int,
    @field:Positive
    val order: Int? = 0,
    val isDefault: Boolean? = true
) {
    fun toDomain() =
        Pokemon(
            name = this.name,
            speciesId = this.speciesId ?: 0,
            height = this.height,
            weight = this.weight,
            baseExperience = this.baseExperience,
            order = this.order ?: 0,
            isDefault = this.isDefault ?: true
        )
}