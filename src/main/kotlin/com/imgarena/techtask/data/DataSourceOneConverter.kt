package com.imgarena.techtask.data

import com.imgarena.techtask.domain.GolfTournament
import com.imgarena.techtask.model.DataSourceOne
import org.springframework.stereotype.Component

@Component
class DataSourceOneConverter : GolfTournamentConverter<DataSourceOne>() {
    override fun getDataSourceId(): String =
        "data-source-one"

    override fun convert(body: String): GolfTournament =
        with(this.toEntity(body)) {
            GolfTournament(
                id = 1,
                externalId = this.tournamentId,
                externalSource = getDataSourceId(),
                courseName = this.courseName,
                eventName = this.tournamentName,
                countryName = this.countryCode,
                startDate = this.startDate,
                endDate = this.endDate,
                rounds = this.roundCount
            )
        }

    override fun getJClass(): Class<DataSourceOne> =
        DataSourceOne::class.java
}