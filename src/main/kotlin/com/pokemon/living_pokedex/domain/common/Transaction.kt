package com.pokemon.living_pokedex.domain.common

interface Transaction {
    fun <T> execute(action: () -> T): T
}