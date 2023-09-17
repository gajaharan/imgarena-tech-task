package com.imgarena.techtask.controller

import com.imgarena.techtask.service.GolfTournamentService
import lombok.RequiredArgsConstructor
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/golf")
class GolfTournamentController(
    private val golfTournamentService: GolfTournamentService
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping("tournament")
    fun createGolfTournament(
        @RequestHeader(value = DATA_SOURCE_ID_HEADER) dataSourceId: String,
        @RequestBody body: String
    ): ResponseEntity<Void> {
        log.info("Receiving a request from $dataSourceId with payload: $body")
        golfTournamentService.save(dataSourceId, body)
        return ResponseEntity.ok().build()
    }

    companion object {
        const val DATA_SOURCE_ID_HEADER = "x-data-src-id"
    }
}