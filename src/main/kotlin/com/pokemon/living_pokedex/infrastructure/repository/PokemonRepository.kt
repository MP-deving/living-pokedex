package com.pokemon.living_pokedex.infrastructure.repository

import com.pokemon.living_pokedex.infrastructure.entities.Pokemon
import com.pokemon.living_pokedex.infrastructure.entities.PokemonIdAndOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface PokemonRepository : JpaRepository<Pokemon, Long?> {
    @Query(
        "SELECT MAX(p.order) FROM Pokemon p"
    )
    fun findLastOrderNumber(): Int?

    @Query(
        "SELECT new com.pokemon.living_pokedex.infrastructure.entities.PokemonIdAndOrder(p.id, p.order) FROM Pokemon p"
    )
    fun findAllIdsAndOrderNumber(): List<PokemonIdAndOrder>

    @Modifying
    @Transactional
    @Query(
        "UPDATE Pokemon p SET p.order = :orderNumber WHERE p.id = :id"
    )
    fun updateOrderNumber(id: Long, orderNumber: Int)

    @Query(
        "SELECT MAX(p.speciesId) FROM Pokemon p"
    )
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