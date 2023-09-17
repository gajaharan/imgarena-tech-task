package com.imgarena.techtask.repository

import com.imgarena.techtask.domain.GolfTournament
import org.springframework.data.jpa.repository.JpaRepository

interface GolfTournamentRepository : JpaRepository<GolfTournament, Long> {
    fun findByExternalId(externalId: String?): GolfTournament?
}