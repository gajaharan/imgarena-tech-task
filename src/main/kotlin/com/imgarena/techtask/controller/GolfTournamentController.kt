package com.imgarena.techtask.controller

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
class GolfTournamentController {
    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping("tournament")
    fun createGolfTournament(
        @RequestHeader(value = "x-data-src-id") dataSourceId: String,
        @RequestBody body: String
    ): ResponseEntity<Void> {
        log.info("Receiving a request from $dataSourceId")
        return ResponseEntity.ok().build()
    }

}