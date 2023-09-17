package com.imgarena.techtask.service

import com.imgarena.techtask.data.GolfTournamentConverter
import com.imgarena.techtask.domain.GolfTournament
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
@RequiredArgsConstructor
class GolfTournamentService(
    private val converters: List<GolfTournamentConverter<*>>
) {
    fun save(dataSourceId: String, body: String) : GolfTournament {
        if (dataSourceConverters().containsKey(dataSourceId)) {
            val converter = dataSourceConverters()[dataSourceId]

            return converter!!.convert(body)
        } else {
            throw Exception("")
        }
    }

    private fun dataSourceConverters() : Map<String, GolfTournamentConverter<*>> =
        converters.associateBy { it.getDataSourceId() }
}