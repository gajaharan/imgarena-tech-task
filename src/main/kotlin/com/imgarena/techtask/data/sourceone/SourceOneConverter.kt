package com.imgarena.techtask.data.sourceone

import com.fasterxml.jackson.annotation.JsonFormat
import com.imgarena.techtask.data.GolfTournamentConverter
import com.imgarena.techtask.data.Source
import com.imgarena.techtask.domain.GolfTournament
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Component
class SourceOneConverter : GolfTournamentConverter<SourceOne>() {
    override fun dataSourceId(): String = DATA_SOURCE_ONE

    override fun convert(body: String): GolfTournament =
        with(this.toEntity(body)) {
            GolfTournament(
                id = 1,
                externalId = this.tournamentId,
                externalSource = dataSourceId(),
                courseName = this.courseName,
                eventName = this.tournamentName,
                countryName = toCountryName(this.countryCode),
                startDate = this.startDateTime,
                endDate = this.endDateTime,
                rounds = this.roundCount
            )
        }

    private fun toCountryName(countryCode: String): String {
        return Locale.getAvailableLocales()
            .find { locale ->
                locale.country.equals(countryCode.uppercase(Locale.ROOT))
            }?.displayCountry ?: "Other"

    }

    override fun jClass(): Class<SourceOne> =
        SourceOne::class.java

    companion object {
        const val DATA_SOURCE_ONE = "data-source-one"
    }
}

data class SourceOne(
    val tournamentId: String,
    val tournamentName: String,
    val forecast: String,
    val courseName: String,
    val countryCode: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy")
    val startDate: LocalDate,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy")
    val endDate: LocalDate,
    val roundCount: Int
) : Source() {
    val startDateTime: LocalDateTime = startDate.atStartOfDay()
    val endDateTime: LocalDateTime = endDate.atStartOfDay()
}