package com.imgarena.techtask.controller

import com.imgarena.techtask.service.GolfTournamentService
import lombok.RequiredArgsConstructor
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/golf")
class GolfTournamentController(
    private val golfTournamentService: GolfTournamentService
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping("tournament")
    fun createGolfTournament(
        uriComponentsBuilder: UriComponentsBuilder,
        @RequestHeader(value = DATA_SOURCE_ID_HEADER) dataSourceId: String,
        @RequestBody body: String
    ): ResponseEntity<Void> {
        log.info("Receiving a request from $dataSourceId with payload: $body")
        val gameTournamentId = golfTournamentService.save(dataSourceId, body)
        val uriComponents = uriComponentsBuilder.path("/{id}").buildAndExpand(gameTournamentId)
        return ResponseEntity.created(uriComponents.toUri()).build()
    }

    companion object {
        const val DATA_SOURCE_ID_HEADER = "x-data-src-id"
    }
}