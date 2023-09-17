package com.imgarena.techtask.service

import com.imgarena.techtask.data.DataSourceOneConverter
import com.imgarena.techtask.data.DataSourceTwoConverter
import com.imgarena.techtask.domain.GolfTournament
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.Instant

internal class GolfTournamentServiceTest {
    private lateinit var golfTournamentService: GolfTournamentService

    @BeforeEach
    fun setUp() {
        golfTournamentService = GolfTournamentService(
            listOf(DataSourceOneConverter(), DataSourceTwoConverter())
        )
    }

    @Test
    fun `should convert the data source one event data to data entity`() {
        var body =
            """
                {
                	"tournamentId": "174638",
                	"tournamentName": "Women's Open Championship",
                	"forecast": "fair",
                	"courseName": "Sunnydale Golf Course",
                	"countryCode": "GB",
                	"startDate": "09/07/21",
                	"endDate": "13/07/21",
                	"roundCount": "4"
                }
            """.trimIndent()


        val actual: GolfTournament = golfTournamentService.save("data-source-one", body)

        assertNotNull(actual)
        assertEquals("174638", actual.externalId)
        assertEquals("Sunnydale Golf Course", actual.courseName)
        assertEquals("2021-07-09", actual.startDate.toString())
    }

    @Test
    fun `should convert the data source two event data to data entity`() {
        var body =
            """
                {
                    "tournamentUUID":"87fc6650-e114-4179-9aef-6a9a13030f80",
                    "golfCourse":"Happy Days Golf Club",
                    "competitionName":"South West Invitational",
                    "hostCountry":"United States Of America",
                    "epochStart":"1638349200",
                    "epochFinish":"1638468000",
                    "rounds":"2",
                    "playerCount":"35"
                }
            """.trimIndent()


        val actual: GolfTournament = golfTournamentService.save("data-source-two", body)

        assertNotNull(actual)
        assertEquals("87fc6650-e114-4179-9aef-6a9a13030f80", actual.externalId)
        assertEquals("Happy Days Golf Club", actual.courseName)
    }
}