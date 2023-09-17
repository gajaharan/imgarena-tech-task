package com.imgarena.techtask.service

import com.imgarena.techtask.data.sourceone.SourceOneConverter
import com.imgarena.techtask.data.sourcetwo.SourceTwoConverter
import com.imgarena.techtask.exception.ConverterNotFoundException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GolfTournamentServiceTest {
    private lateinit var golfTournamentService: GolfTournamentService

    @BeforeEach
    fun setUp() {
        golfTournamentService = GolfTournamentService(
            listOf(SourceOneConverter(), SourceTwoConverter())
        )
    }

    @Test
    fun `invalid data souce id should throw ConverterNotFoundException`() {
        assertThrows(ConverterNotFoundException::class.java) {
            golfTournamentService.save("invalid-data-source-id", "")
        }.let { exception ->
            assertEquals(
                "Converter not found for data source id: invalid-data-source-id"
                , exception.message
            )
        }
    }
}