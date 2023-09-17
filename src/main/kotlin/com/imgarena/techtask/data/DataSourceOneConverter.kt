package com.imgarena.techtask.data

import com.imgarena.techtask.data.JacksonConfiguration.Companion.objectMapper
import com.imgarena.techtask.domain.GolfTournament
import com.imgarena.techtask.model.DataSourceOne
import org.springframework.stereotype.Component

@Component
class DataSourceOneConverter {

    fun toEntity(body: String): GolfTournament {
        val data = objectMapper.readValue(body, DataSourceOne::class.java)
        return GolfTournament(
            id = 1,
            externalId = data.tournamentId,
            externalSource = "data-source-one",
            courseName = data.courseName,
            eventName = data.tournamentName,
            countryName = data.countryCode,
            startDate = data.startDate,
            endDate = data.endDate,
            rounds = data.roundCount
        )
    }

}