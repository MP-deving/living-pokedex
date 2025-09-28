package com.pokemon.living_pokedex.web.controller

import com.pokemon.living_pokedex.domain.usecase.ImportPokemonFromCSVFileUseCase
import java.io.BufferedReader
import java.io.InputStreamReader
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/import")
class ImportController(
    private val importPokemonFromCSVFileUseCase: ImportPokemonFromCSVFileUseCase
) {

    @PostMapping("from-csv")
    fun importFromCsv(
        @RequestParam("file") file: MultipartFile
    ): ResponseEntity<Unit?> {
        val reader = BufferedReader(InputStreamReader(file.inputStream))
        return ResponseEntity(importPokemonFromCSVFileUseCase.execute(reader), HttpStatus.CREATED)
    }
}