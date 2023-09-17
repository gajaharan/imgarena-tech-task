package com.imgarena.techtask.service

import com.imgarena.techtask.data.GolfTournamentConverter
import com.imgarena.techtask.data.Source
import com.imgarena.techtask.exception.ConverterNotFoundException
import com.imgarena.techtask.repository.GolfTournamentRepository
import lombok.RequiredArgsConstructor
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class GolfTournamentService(
    private val converters: List<GolfTournamentConverter<out Source>>,
    private val repository: GolfTournamentRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun save(dataSourceId: String, body: String) : Long {
        if (dataSourceConverters().containsKey(dataSourceId)) {
            dataSourceConverters()[dataSourceId].let { converter ->
                converter!!.convert(body).let { golfTournament ->
                    return repository.save(golfTournament).id
                }
            }
        } else {
            log.warn("Converter not found for data source id: $dataSourceId")
            throw ConverterNotFoundException("Converter not found for data source id: $dataSourceId")
        }
    }

    private fun dataSourceConverters() : Map<String, GolfTournamentConverter<out Source>> =
        converters.associateBy { it.dataSourceId() }
}