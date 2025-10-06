package com.pokemon.living_pokedex.domain.usecase

import com.pokemon.living_pokedex.domain.gateway.StorePokemonGateway
import com.pokemon.living_pokedex.domain.model.Pokemon
import org.springframework.stereotype.Component

@Component
class CreatePokemonUseCase(
    private val storePokemonGateway: StorePokemonGateway
) {
    fun execute(pokemonDomain: Pokemon){
        storePokemonGateway.execute(pokemonDomain)
    }
}