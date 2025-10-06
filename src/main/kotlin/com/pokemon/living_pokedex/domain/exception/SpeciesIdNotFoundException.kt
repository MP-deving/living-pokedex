package com.pokemon.living_pokedex.domain.exception

class SpeciesIdNotFoundException(
    message: String?
) : RuntimeException(message ?: "Species ID not found")