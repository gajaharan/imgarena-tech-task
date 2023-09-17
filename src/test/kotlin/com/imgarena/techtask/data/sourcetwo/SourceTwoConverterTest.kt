package com.imgarena.techtask.data.sourcetwo

import com.imgarena.techtask.TestFixtures.dataSourceTwoBody
import com.imgarena.techtask.domain.GolfTournament
import com.imgarena.techtask.exception.MappingException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

class SourceTwoConverterTest {
    private val converter: SourceTwoConverter = SourceTwoConverter()

    @Test
    fun `should convert the data source two event data to data entity`() {
        val body = dataSourceTwoBody

        val actual: GolfTournament = converter.convert(body)

        Assertions.assertNotNull(actual)
        Assertions.assertEquals("87fc6650-e114-4179-9aef-6a9a13030f80", actual.externalId)
        Assertions.assertEquals("Happy Days Golf Club", actual.courseName)
        Assertions.assertEquals("1638349200", actual.startDate.toEpoch().toString())
    }

    @Test
    fun `should throw MappingException when converting invalid json body`() {
        val body = """{ "tournamentUUID":  } """.trim()

        Assertions.assertThrows (MappingException::class.java) {
            converter.convert(body)
        }
    }

    private fun LocalDateTime.toEpoch(): Long = this.toEpochSecond(ZoneOffset.UTC)
}