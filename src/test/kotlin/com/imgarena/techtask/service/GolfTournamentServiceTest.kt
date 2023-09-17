package com.imgarena.techtask.service

import com.imgarena.techtask.data.sourceone.SourceOneConverter
import com.imgarena.techtask.data.sourcetwo.SourceTwoConverter
import com.imgarena.techtask.exception.ConverterNotFoundException
import com.imgarena.techtask.repository.GolfTournamentRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

internal class GolfTournamentServiceTest {
    private lateinit var golfTournamentService: GolfTournamentService
    private lateinit var repository: GolfTournamentRepository

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(GolfTournamentRepository::class.java)
        golfTournamentService = GolfTournamentService(
            listOf(SourceOneConverter(), SourceTwoConverter()),
            repository
        )
    }

    @Test
    fun `invalid data source id should throw ConverterNotFoundException`() {
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