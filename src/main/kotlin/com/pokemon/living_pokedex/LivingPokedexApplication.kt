package com.pokemon.living_pokedex

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class LivingPokedexApplication

fun main(args: Array<String>) {
	runApplication<LivingPokedexApplication>(*args)
}
