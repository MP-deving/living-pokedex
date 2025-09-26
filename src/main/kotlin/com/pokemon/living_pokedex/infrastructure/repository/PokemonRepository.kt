package com.pokemon.living_pokedex.infrastructure.repository

import com.pokemon.living_pokedex.infrastructure.entities.Pokemon
import org.springframework.data.mongodb.repository.MongoRepository

interface PokemonRepository: MongoRepository<Pokemon, String> {
    fun findByPokedexId(pokedexId: Int): Pokemon?
}