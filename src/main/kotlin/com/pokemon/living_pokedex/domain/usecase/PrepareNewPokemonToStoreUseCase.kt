package com.pokemon.living_pokedex.domain.usecase

import com.pokemon.living_pokedex.domain.exception.OrderNumberNotFoundException
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

        val lastSpeciesId = findLastSpeciesIdToInsertInNewPokemon()

        return pokemonDomain.copy(
            order = orderNumberToInsert,
            speciesId = if (pokemonDomain.speciesId == 0) lastSpeciesId + 1 else pokemonDomain.speciesId
        )
    }

    private fun findLastOrderNumberToInsertInNewPokemon(): Int {
        val lastOrderNumber = pokemonRepository.findLastOrderNumber()
        return lastOrderNumber
            ?: throw OrderNumberNotFoundException("Error: Last order number not found or invalid order number provided.")
    }

    private fun findLastSpeciesIdToInsertInNewPokemon(): Int {
        return pokemonRepository.findLastSpeciesId()
            ?: throw SpeciesIdNotFoundException("Error: Last species ID not found.")
    }


    private fun setOrderNumberToNewPokemon(orderNumberReceived: Int, lastOrderNumber: Int): Int {
        return if (orderNumberReceived == 0)
            lastOrderNumber + 1
        else
            orderNumberReceived
    }
}