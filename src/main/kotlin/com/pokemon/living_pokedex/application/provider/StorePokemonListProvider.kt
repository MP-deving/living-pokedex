package com.pokemon.living_pokedex.application.provider

import com.pokemon.living_pokedex.domain.gateway.StorePokemonListGateway
import com.pokemon.living_pokedex.domain.model.Pokemon
import com.pokemon.living_pokedex.infrastructure.repository.PokemonRepository
import org.springframework.stereotype.Component

@Component
class StorePokemonListProvider(
    private val pokemonRepository: PokemonRepository
): StorePokemonListGateway {
    override fun execute(pokemonList: List<Pokemon>) {
        pokemonRepository.saveAll(
            pokemonList.map {
                it.toEntity()
            }
        )
    }
}