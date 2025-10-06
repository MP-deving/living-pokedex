package com.pokemon.living_pokedex.domain.exception

class OrderNumberNotFoundException(message: String?) : RuntimeException(message ?: "Order number not found")