package com.imgarena.techtask.data.sourceone

import com.imgarena.techtask.TestFixtures.dataSourceOneBody
import com.imgarena.techtask.domain.GolfTournament
import com.imgarena.techtask.exception.MappingException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SourceOneConverterTest {
    private val converter: SourceOneConverter  = SourceOneConverter()

    @Test
    fun `should convert the data source one event data to data entity`() {
        val body = dataSourceOneBody

        val actual: GolfTournament = converter.convert(body)

        Assertions.assertNotNull(actual)
        Assertions.assertEquals("174638", actual.externalId)
        Assertions.assertEquals("Sunnydale Golf Course", actual.courseName)
        Assertions.assertEquals("2021-07-09T00:00", actual.startDate.toString())
        Assertions.assertEquals("United Kingdom", actual.countryName)
    }

    @Test
    fun `should throw MappingException when converting invalid json body`() {
        val body = """{ "tournamentId":  } """.trim()

        Assertions.assertThrows (MappingException::class.java) {
            converter.convert(body)
        }
    }
}