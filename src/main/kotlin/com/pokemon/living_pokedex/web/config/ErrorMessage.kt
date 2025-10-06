package com.pokemon.living_pokedex.web.config

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.Date

data class ErrorMessage (
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    val timestamp: Date = Date(),
    val code: String,
    val status: Int,
    val message: String
)