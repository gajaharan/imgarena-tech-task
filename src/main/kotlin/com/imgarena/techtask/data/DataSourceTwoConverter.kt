package com.imgarena.techtask.data

import com.imgarena.techtask.domain.GolfTournament
import com.imgarena.techtask.model.DataSourceTwo
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

@Component
class DataSourceTwoConverter : GolfTournamentConverter<DataSourceTwo>() {
    override fun getDataSourceId(): String =
        "data-source-two"

    override fun convert(body: String): GolfTournament =
        with(this.toEntity(body)) {
            GolfTournament(
                id = 2,
                externalId = this.tournamentUUID,
                externalSource = getDataSourceId(),
                courseName = this.golfCourse,
                eventName = this.competitionName,
                countryName = this.hostCountry,
                startDate = this.epochStart.toLocalDate(),
                endDate = this.epochFinish.toLocalDate(),
                rounds = this.rounds
            )
        }

    override fun getJClass(): Class<DataSourceTwo> =
        DataSourceTwo::class.java

    fun Instant.toLocalDate(): LocalDate =
        this.atZone(ZoneOffset.systemDefault()).toLocalDate()
}