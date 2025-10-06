package com.pokemon.living_pokedex.domain.usecase

import com.pokemon.living_pokedex.domain.exception.OrderNumberNotFoundException
import com.pokemon.living_pokedex.domain.exception.PokedexIdNotFoundException
import com.pokemon.living_pokedex.domain.exception.SpeciesIdNotFoundException
import com.pokemon.living_pokedex.domain.model.Pokemon
import com.pokemon.living_pokedex.infrastructure.repository.PokemonRepository

class PrepareNewPokemonToStoreUseCase(
    private val pokemonRepository: PokemonRepository,
    private val checkOrderNumberReorderUseCase: CheckOrderNumberReorderUseCase
) {
    fun execute(pokemonDomain: Pokemon): Pokemon {
        val lastOrderNumber = findLastOrderNumberToInsertInNewPokemon()

        val orderNumberToInsert = setOrderNumberToNewPokemon(pokemonDomain.order, lastOrderNumber)
        checkOrderNumberReorderUseCase.execute(orderNumberToInsert, lastOrderNumber)

        val lastSpeciesId = findLastSpeciesIdToInsertInNewPokemon(pokemonDomain.speciesId)
        val pokedexIdToInsert = findPokedexIdToInsertInNewPokemon(pokemonDomain.isDefault)

        return pokemonDomain.copy(
            order = orderNumberToInsert,
            speciesId = lastSpeciesId,
            pokedexId = pokedexIdToInsert
        )
    }

    private fun findLastOrderNumberToInsertInNewPokemon(): Int {
        val lastOrderNumber = pokemonRepository.findLastOrderNumber()
        return lastOrderNumber
            ?: throw OrderNumberNotFoundException("Error: Last order number not found or invalid order number provided.")
    }

    private fun findLastSpeciesIdToInsertInNewPokemon(speciesIdReceived: Int): Int {
        if (speciesIdReceived == 0) {
            val lastCurrentSpeciesId = pokemonRepository.findLastSpeciesId()
                ?: throw SpeciesIdNotFoundException("Error: Last species ID not found.")
            return lastCurrentSpeciesId + 1
        } else {
            return speciesIdReceived
        }
    }

    private fun findPokedexIdToInsertInNewPokemon(isDefault: Boolean): Int {
        if (isDefault) {
            val lastPokedexId = pokemonRepository.findLastDefaultPokemonPokedexId()
            return lastPokedexId?.plus(1)
                ?: throw PokedexIdNotFoundException("Error: Last Pokédex ID for default Pokémon not found.")
        } else {
            val lastOrderNumber = pokemonRepository.findLastNonDefaultPokemonPokedexId()
            return lastOrderNumber?.plus(1)
                ?: throw PokedexIdNotFoundException("Error: Last Pokédex ID for non")
        }
    }

    private fun setOrderNumberToNewPokemon(orderNumberReceived: Int, lastOrderNumber: Int): Int {
        return if (orderNumberReceived == 0)
            lastOrderNumber + 1
        else
            orderNumberReceived
    }
}
