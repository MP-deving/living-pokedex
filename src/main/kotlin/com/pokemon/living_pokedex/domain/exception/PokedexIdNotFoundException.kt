package com.pokemon.living_pokedex.domain.exception

class PokedexIdNotFoundException(
    message: String? = "Pokedex ID not found"
) : RuntimeException(message)