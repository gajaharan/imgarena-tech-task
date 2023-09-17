package com.imgarena.techtask.data.sourcetwo

import com.imgarena.techtask.data.GolfTournamentConverter
import com.imgarena.techtask.data.Source
import com.imgarena.techtask.domain.GolfTournament
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Component
class SourceTwoConverter : GolfTournamentConverter<SourceTwo>() {
    override fun dataSourceId(): String = DATA_SOURCE_TWO

    override fun convert(body: String): GolfTournament =
        with(this.toEntity(body)) {
            GolfTournament(
                id = 2,
                externalId = this.tournamentUUID,
                externalSource = dataSourceId(),
                courseName = this.golfCourse,
                eventName = this.competitionName,
                countryName = this.hostCountry,
                startDate = this.epochStart.toLocalDateTime(),
                endDate = this.epochFinish.toLocalDateTime(),
                rounds = this.rounds
            )
        }

    override fun jClass(): Class<SourceTwo> =
        SourceTwo::class.java

    fun Instant.toLocalDateTime(): LocalDateTime =
        this.atZone(ZoneOffset.systemDefault()).toLocalDateTime()

    companion object {
        private const val DATA_SOURCE_TWO = "data-source-two"
    }
}

data class SourceTwo(
    val tournamentUUID: String,
    val golfCourse: String,
    val competitionName: String,
    val hostCountry: String,
    val epochStart: Instant,
    val epochFinish: Instant,
    val rounds: Int,
    val playerCount: Int
) : Source()