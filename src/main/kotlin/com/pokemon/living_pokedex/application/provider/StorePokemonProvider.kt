package com.pokemon.living_pokedex.application.provider

import com.pokemon.living_pokedex.domain.common.Transaction
import com.pokemon.living_pokedex.domain.gateway.StorePokemonGateway
import com.pokemon.living_pokedex.domain.model.Pokemon
import com.pokemon.living_pokedex.domain.usecase.PrepareNewPokemonToStoreUseCase
import com.pokemon.living_pokedex.infrastructure.repository.PokemonRepository
import org.springframework.stereotype.Component

@Component
class StorePokemonProvider(
    private val pokemonRepository: PokemonRepository,
    private val prepareNewPokemonToStoreUseCase: PrepareNewPokemonToStoreUseCase,
    private val transaction: Transaction
) : StorePokemonGateway {
    override fun execute(pokemonDomain: Pokemon) {
        transaction.run {
            val pokemonToStore = prepareNewPokemonToStoreUseCase.execute(pokemonDomain)
            pokemonRepository.save(pokemonToStore.toEntity())
        }
    }
}