package com.imgarena.techtask.integration

import com.imgarena.techtask.TestFixtures.dataSourceOneBody
import com.imgarena.techtask.TestFixtures.dataSourceTwoBody
import com.imgarena.techtask.controller.GolfTournamentController.Companion.DATA_SOURCE_ID_HEADER
import com.imgarena.techtask.data.sourceone.SourceOneConverter.Companion.DATA_SOURCE_ONE
import com.imgarena.techtask.data.sourcetwo.SourceTwoConverter.Companion.DATA_SOURCE_TWO
import com.imgarena.techtask.domain.GolfTournament
import com.imgarena.techtask.repository.GolfTournamentRepository
import com.imgarena.techtask.service.GolfTournamentService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GolfTournamentIntegrationTest {
    @Autowired
    private lateinit var service: GolfTournamentService

    @Autowired
    private lateinit var repository: GolfTournamentRepository

    @Autowired
    private lateinit var  restTemplate: TestRestTemplate

    @LocalServerPort
    private var randomServerPort = 0

    @AfterEach
    fun clearUp() {
        repository.deleteAll()
    }

    @Test
    fun `should return 201 create response when saving golf tournament data for source 1`() {
        val requestEntity: HttpEntity<String> = HttpEntity(dataSourceOneBody, headers(DATA_SOURCE_ONE))
        val response = sendRequest(requestEntity)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(listOf("http://localhost:$randomServerPort/1"), response.headers["Location"])
        val actual: GolfTournament? = repository.findByExternalId("174638")
        assertNotNull(actual)
        assertEquals(1, actual?.id)
        assertEquals("174638", actual?.externalId)
    }

    @Test
    fun `should return 201 create response when saving golf tournament data for source 2`() {
        val requestEntity: HttpEntity<String> = HttpEntity(dataSourceTwoBody, headers(DATA_SOURCE_TWO))
        val response = sendRequest(requestEntity)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(listOf("http://localhost:$randomServerPort/2"), response.headers["Location"])
        val actual: GolfTournament? = repository.findByExternalId("87fc6650-e114-4179-9aef-6a9a13030f80")
        assertNotNull(actual)
        assertEquals(2, actual?.id)
        assertEquals("87fc6650-e114-4179-9aef-6a9a13030f80", actual?.externalId)
    }

    private fun sendRequest(requestEntity: HttpEntity<String>) =
        restTemplate!!.exchange(
            "http://localhost:$randomServerPort/api/v1/golf/tournament",
            HttpMethod.POST,
            requestEntity,
            Void::class.java
        )

    private fun headers(dataSourceId: String): HttpHeaders? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.add(DATA_SOURCE_ID_HEADER, dataSourceId)
        return headers
    }
}