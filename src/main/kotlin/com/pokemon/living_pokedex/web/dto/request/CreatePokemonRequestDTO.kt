package com.pokemon.living_pokedex.web.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class CreatePokemonRequestDTO (
    @field:NotBlank
    val name: String,
    val speciesId: Int?,
    @field:NotNull
    @field:Positive
    val height: Int,
    @field:NotNull
    @field:Positive
    val weight: Int,
    @field:NotNull
    @field:Positive
    val baseExperience: Int,
)