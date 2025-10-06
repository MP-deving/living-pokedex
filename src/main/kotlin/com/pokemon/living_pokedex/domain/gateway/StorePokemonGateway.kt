package com.pokemon.living_pokedex.domain.gateway

import com.pokemon.living_pokedex.domain.model.Pokemon

interface StorePokemonGateway {
    fun execute(pokemonDomain: Pokemon)
}