package com.pokemon.living_pokedex.domain.usecase

import com.pokemon.living_pokedex.domain.gateway.StorePokemonListGateway
import com.pokemon.living_pokedex.domain.model.Pokemon
import java.io.BufferedReader
import org.springframework.stereotype.Component

@Component
class ImportPokemonFromCSVFileUseCase(
    private val storePokemonListGateway: StorePokemonListGateway
) {

    fun execute(buffered: BufferedReader) {
        val pokemonListFromBuferred = buffered.lineSequence()
            .drop(1)
            .map { line ->
                val columns = line.split(",")
                Pokemon(
                    pokedexId = columns[0].toInt(),
                    name = columns[1],
                    speciesId = columns[2].toInt(),
                    height = columns[3].toInt(),
                    weight = columns[4].toInt(),
                    baseExperience = columns[5].toInt(),
                    order = columns[6].toInt(),
                    isDefault = columns[7].toBoolean()
                )
            }.toList()
        storePokemonListGateway.execute(pokemonListFromBuferred)
    }
}