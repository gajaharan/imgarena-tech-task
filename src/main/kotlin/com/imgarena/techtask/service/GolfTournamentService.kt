package com.imgarena.techtask.service

import com.imgarena.techtask.data.GolfTournamentConverter
import com.imgarena.techtask.data.Source
import com.imgarena.techtask.domain.GolfTournament
import com.imgarena.techtask.exception.ConverterNotFoundException
import lombok.RequiredArgsConstructor
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class GolfTournamentService(
    private val converters: List<GolfTournamentConverter<out Source>>
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun save(dataSourceId: String, body: String) : GolfTournament {
        if (dataSourceConverters().containsKey(dataSourceId)) {
            val converter = dataSourceConverters()[dataSourceId]
            return converter!!.convert(body)
        } else {
            log.warn("Converter not found for data source id: $dataSourceId")
            throw ConverterNotFoundException("Converter not found for data source id: $dataSourceId")
        }
    }

    private fun dataSourceConverters() : Map<String, GolfTournamentConverter<out Source>> =
        converters.associateBy { it.dataSourceId() }
}