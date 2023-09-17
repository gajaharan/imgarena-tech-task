package com.imgarena.techtask.controller

import com.imgarena.techtask.TestFixtures.dataSourceOneBody
import com.imgarena.techtask.controller.GolfTournamentController.Companion.DATA_SOURCE_ID_HEADER
import com.imgarena.techtask.data.sourceone.SourceOneConverter.Companion.DATA_SOURCE_ONE
import com.imgarena.techtask.exception.ConverterNotFoundException
import com.imgarena.techtask.exception.MappingException
import com.imgarena.techtask.service.GolfTournamentService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(GolfTournamentController::class)
internal class GolfTournamentControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var gameTournamentService: GolfTournamentService

    @Test
    fun `should return 201 create response when creating a golf tournament`() {
        val actual = mockMvc.perform(
            MockMvcRequestBuilders
                .post("/api/v1/golf/tournament")
                .contentType(MediaType.APPLICATION_JSON)
                .header(DATA_SOURCE_ID_HEADER, DATA_SOURCE_ONE)
                .content(dataSourceOneBody)
        )

        actual.andExpect(status().isCreated)
            .andExpect(
                MockMvcResultMatchers.header()
                    .stringValues("Location", "http://localhost/0")
            );
    }

    @Test
    fun `should return 400 bad request when passing invalid data source id`() {
        given(gameTournamentService.save("invalid-data-source", dataSourceOneBody))
            .willThrow(ConverterNotFoundException("invalid data source"))

        val actual = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/golf/tournament")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dataSourceOneBody)
                .header(DATA_SOURCE_ID_HEADER, "invalid-data-source")
        )

        actual.andExpect(status().isBadRequest)
            .andExpect(content().string("invalid data source"))
    }

    @Test
    fun `should return 400 bad request when invalid json body is sent`() {
        val invalidJsonBody = """{ }"""
        given(gameTournamentService.save(DATA_SOURCE_ONE, invalidJsonBody))
            .willThrow(MappingException("Invalid json body", Exception()))

        val actual = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/golf/tournament")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJsonBody)
                .header(DATA_SOURCE_ID_HEADER, DATA_SOURCE_ONE)
        )

        actual.andExpect(status().isBadRequest)
            .andExpect(content().string("Invalid json body"))
    }
}