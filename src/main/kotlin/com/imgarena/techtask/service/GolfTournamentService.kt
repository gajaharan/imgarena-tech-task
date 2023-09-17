package com.imgarena.techtask.service

import com.imgarena.techtask.data.DataSourceOneConverter
import com.imgarena.techtask.domain.GolfTournament
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class GolfTournamentService(
    private val converter: DataSourceOneConverter
) {
    fun save(dataSourceId: String, body: String) : GolfTournament =
        converter.toEntity(body)
}