package com.pokemon.living_pokedex.infrastructure.repository

import com.pokemon.living_pokedex.infrastructure.entities.Pokemon
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonRepository : JpaRepository<Pokemon, Long?> {
    fun findByName(name: String): Pokemon?
}