package com.pokemon.living_pokedex.domain.gateway

import com.pokemon.living_pokedex.domain.model.Pokemon

interface StorePokemonListGateway {
    fun execute(pokemonList: List<Pokemon>)
}