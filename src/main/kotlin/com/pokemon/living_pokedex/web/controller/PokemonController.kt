package com.pokemon.living_pokedex.web.controller

import com.pokemon.living_pokedex.domain.usecase.CreatePokemonUseCase
import com.pokemon.living_pokedex.web.dto.request.CreatePokemonRequestDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pokemon")
class PokemonController(
    private val createPokemonUseCase: CreatePokemonUseCase
) {

    @PostMapping
    fun createPokemon(
        @RequestBody(required = true) request: CreatePokemonRequestDTO
    ) = ResponseEntity(
        createPokemonUseCase.execute(request.toDomain()),
        HttpStatus.CREATED
    )
}