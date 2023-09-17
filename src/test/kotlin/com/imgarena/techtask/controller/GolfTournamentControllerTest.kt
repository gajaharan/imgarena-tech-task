package com.imgarena.techtask.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(GolfTournamentController::class)
internal class GolfTournamentControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should return 200 success response when creating a golf tournament`() {
        val actual = mockMvc.perform(
            MockMvcRequestBuilders
                .post("/api/v1/golf/tournament")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-data-src-id", "data-source-one")
                .content(
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
                )
        )

        actual.andExpect(status().isOk)

    }
}