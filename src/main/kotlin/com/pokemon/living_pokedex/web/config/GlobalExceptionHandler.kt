package com.pokemon.living_pokedex.web.config

import com.pokemon.living_pokedex.domain.exception.OrderNumberNotFoundException
import com.pokemon.living_pokedex.domain.exception.SpeciesIdNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(value = [OrderNumberNotFoundException::class])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun orderNumberNotFoundExceptionHandler(e: OrderNumberNotFoundException): ResponseEntity<ErrorMessage> {
        logger.error("OrderNumberNotFoundException: ", e)
        return ResponseEntity(
            ErrorMessage(
                code = HttpStatus.NOT_FOUND.name,
                status = HttpStatus.NOT_FOUND.value(),
                message = e.message ?: "Order number not found",
            ),
            HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(value = [SpeciesIdNotFoundException::class])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun speciesIdNotFoundException(e: SpeciesIdNotFoundException): ResponseEntity<ErrorMessage> {
        logger.error("SpeciesIdNotFoundException: ", e)
        return ResponseEntity(
            ErrorMessage(
                code = HttpStatus.NOT_FOUND.name,
                status = HttpStatus.NOT_FOUND.value(),
                message = e.message ?: "SpeciesId not found",
            ),
            HttpStatus.NOT_FOUND
        )
    }
}