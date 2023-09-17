package com.imgarena.techtask.controller

import com.imgarena.techtask.TestFixtures.dataSourceOneBody
import com.imgarena.techtask.controller.GolfTournamentController.Companion.DATA_SOURCE_ID_HEADER
import com.imgarena.techtask.data.sourceone.SourceOneConverter.Companion.DATA_SOURCE_ONE
import com.imgarena.techtask.service.GolfTournamentService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(GolfTournamentController::class)
internal class GolfTournamentControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var gameTournamentService: GolfTournamentService

    @Test
    fun `should return 200 success response when creating a golf tournament`() {
        val actual = mockMvc.perform(
            MockMvcRequestBuilders
                .post("/api/v1/golf/tournament")
                .contentType(MediaType.APPLICATION_JSON)
                .header(DATA_SOURCE_ID_HEADER, DATA_SOURCE_ONE)
                .content(dataSourceOneBody)
        )

        actual.andExpect(status().isOk)
    }
}