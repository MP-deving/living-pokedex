package com.pokemon.living_pokedex.domain.usecase

import com.pokemon.living_pokedex.infrastructure.repository.PokemonRepository

class CheckOrderNumberReorderUseCase(
    private val pokemonRepository: PokemonRepository
) {
    fun execute(orderNumber: Int, lastOrderNumber: Int) {
        if(orderNumber <= lastOrderNumber){
           val allIdsAndOrderNumbers = pokemonRepository.findAllIdsAndOrderNumber()
              val listToReorder = allIdsAndOrderNumbers.filter { it.orderNumber >= orderNumber }
            listToReorder.map {
                pokemonRepository.updateOrderNumber(it.id, it.orderNumber + 1)
            }
        }
    }
}
