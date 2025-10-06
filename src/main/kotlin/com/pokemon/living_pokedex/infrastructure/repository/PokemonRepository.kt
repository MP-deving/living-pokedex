package com.pokemon.living_pokedex.infrastructure.repository

import com.pokemon.living_pokedex.infrastructure.entities.Pokemon
import com.pokemon.living_pokedex.infrastructure.entities.PokemonIdAndOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PokemonRepository : JpaRepository<Pokemon, Long?> {
    fun findByName(name: String): Pokemon?
    fun findLastOrderNumber(): Int?
    fun findAllIdsAndOrderNumber(): List<PokemonIdAndOrder>
    fun updateOrderNumber(id: Long, orderNumber: Int)
    fun findLastSpeciesId(): Int?

    @Query(
        "SELECT MAX(p.pokedexId) FROM Pokemon p WHERE p.isDefault = true"
    )
    fun findLastDefaultPokemonPokedexId(): Int?

    @Query(
        "SELECT MAX(p.pokedexId) FROM Pokemon p WHERE p.isDefault = false"
    )
    fun findLastNonDefaultPokemonPokedexId(): Int?
}